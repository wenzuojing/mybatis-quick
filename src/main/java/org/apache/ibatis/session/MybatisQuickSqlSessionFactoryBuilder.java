package org.apache.ibatis.session;



/**
 *
 * @author wens
 * @Date 2018-10-10
 */
public class MybatisQuickSqlSessionFactoryBuilder extends SqlSessionFactoryBuilder {

    @Override
    public SqlSessionFactory build(Configuration config) {
        return super.build(new DelegateConfiguration( config ));
    }
}
