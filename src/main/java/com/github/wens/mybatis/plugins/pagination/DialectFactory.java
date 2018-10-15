/**
 * Copyright (c) 2011-2014, hubin (jobob@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.wens.mybatis.plugins.pagination;

import com.github.wens.mybatis.plugins.pagination.dialects.*;

/**
 * <p>
 * 分页方言工厂类
 * </p>
 *
 * @author hubin
 * @Date 2016-01-23
 */
public class DialectFactory {

    /**
     * <p>
     * 根据数据库类型选择不同分页方言
     * </p>
     *
     * @param dbtype 数据库类型
     * @return
     * @throws Exception
     */
    public static IDialect getDialectByDbtype(String dbtype) throws Exception {
        if ("mysql".equalsIgnoreCase(dbtype)) {
            return new MySqlDialect();
        } else if ("oracle".equalsIgnoreCase(dbtype)) {
            return new OracleDialect();
        } else if ("hsql".equalsIgnoreCase(dbtype)) {
            return new HSQLDialect();
        } else if ("sqlite".equalsIgnoreCase(dbtype)) {
            return new SQLiteDialect();
        } else if ("postgre".equalsIgnoreCase(dbtype)) {
            return new PostgreDialect();
        } else if ("sqlserver".equalsIgnoreCase(dbtype)) {
            return new SQLServerDialect();
        } else {
            return null;
        }
    }

}
