package com.fruit.base.framework.mybatis.interceptor;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.fruit.base.framework.mybatis.page.Page;
import com.fruit.base.framework.mybatis.page.SQLHelper;
import com.fruit.base.framework.utils.reflect.Reflections;

/**
 * 数据库分页插件，只拦截查询语句.
 */
@Intercepts({@Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PaginationInterceptor extends BaseInterceptor {

    private static final long serialVersionUID = 1L;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        final MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        
            //拦截需要分页的SQL
            Object parameter = invocation.getArgs()[1];
            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            Object parameterObject = boundSql.getParameterObject();
            Object parameterPage = null;
            Boolean simpleCountSql = null;
            try {
            	@SuppressWarnings("unchecked")
				Map<String, Object> parameterMap = (Map<String, Object>)parameterObject;
            	parameterPage = parameterMap.get("page");
            	Object parameterSimpleCountSql = parameterMap.get("simpleCountSql");
            	if(null != parameterSimpleCountSql){
            		simpleCountSql = (Boolean)parameterSimpleCountSql;
            	}
			} catch (Exception e) {
			}

            //获取分页参数对象
            Page page = null;
            if (parameterPage != null) {
                page = convertParameter(parameterPage, page);
            }

            //如果设置了分页对象，则进行分页
            if (page != null && page.getPageSize() != -1) {

            	if (StringUtils.isBlank(boundSql.getSql())){
                    return null;
                }
                String originalSql = boundSql.getSql().trim();
            	
                //得到总记录数
                if(null == simpleCountSql || simpleCountSql){
                	page.setTotalCount(SQLHelper.getCount(originalSql, null, mappedStatement, parameterObject, boundSql, log, simpleCountSql));
                }

                //分页查询 本地化对象 修改数据库注意修改实现
                String pageSql = SQLHelper.generatePageSql(originalSql, page, DIALECT);
                invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
                BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), pageSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
                //解决MyBatis 分页foreach 参数失效 start
                if (Reflections.getFieldValue(boundSql, "metaParameters") != null) {
                    MetaObject mo = (MetaObject) Reflections.getFieldValue(boundSql, "metaParameters");
                    Reflections.setFieldValue(newBoundSql, "metaParameters", mo);
                }
                //解决MyBatis 分页foreach 参数失效 end
                MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));

                invocation.getArgs()[0] = newMs;
            }
        return invocation.proceed();
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        super.initProperties(properties);
    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms,
                                                    SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
                ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null) {
            for (String keyProperty : ms.getKeyProperties()) {
                builder.keyProperty(keyProperty);
            }
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());
        return builder.build();
    }

    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
}
