package com.github.wens.mybatis.example;


import com.github.wens.mybatis.TableFieldInfo;
import com.github.wens.mybatis.TableInfo;
import com.github.wens.mybatis.TableInfoHelper;
import com.github.wens.mybatis.support.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wens
 * @Date 2018-10-10
 */
public class Example<T> {

    private static Map<String, String> allSelectColumnCache = new ConcurrentHashMap<>();

    public static <T> Example<T> of(Class<T> entityClass) {
        return new Example<>(entityClass);
    }

    protected String orderByClause;

    protected boolean distinct;

    protected boolean exists;

    protected boolean notNull;

    protected Set<String> selectColumns;

    protected List<Criteria> oredCriteria;

    protected Class<T> entityClass;

    protected TableInfo table;

    protected OrderBy ORDERBY;

    protected Integer limit;


    public Example(Class<T> entityClass) {
        this(entityClass, true);
    }


    public Example(Class<T> entityClass, boolean exists) {
        this(entityClass, exists, false);
    }


    public Example(Class<T> entityClass, boolean exists, boolean notNull) {
        this.exists = exists;
        this.notNull = notNull;
        oredCriteria = new ArrayList<Criteria>();
        this.entityClass = entityClass;
        table = TableInfoHelper.getTableInfo(entityClass);
        this.ORDERBY = new OrderBy(this, table);
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public OrderBy orderBy(String property) {
        this.ORDERBY.orderBy(property);
        return this.ORDERBY;
    }

    public void limit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public String getSelectColumns() {


        if (this.selectColumns == null) {


            return selectAllColumn(table);


        } else {
            StringBuilder columns = new StringBuilder();

            if (table.isKeyRelated()) {
                columns.append(table.getKeyColumn()).append(" AS ").append(table.getKeyProperty());
            } else {
                columns.append(table.getKeyProperty());
            }

            for (String column : selectColumns) {
                columns.append(",").append(column).append(" AS ").append(table.getProperty(column));
            }
            return columns.toString();
        }


    }

    private String selectAllColumn(TableInfo table) {
        String s = allSelectColumnCache.get(table.getTableName());
        if (s != null) {
            return s;
        }

        StringBuilder columns = new StringBuilder();
        if (table.isKeyRelated()) {
            columns.append(table.getKeyColumn()).append(" AS ").append(table.getKeyProperty());
        } else {
            columns.append(table.getKeyProperty());
        }
        List<TableFieldInfo> fieldList = table.getFieldList();
        for (TableFieldInfo fieldInfo : fieldList) {
            columns.append(",").append(fieldInfo.getColumn());
            if (fieldInfo.isRelated()) {
                columns.append(" AS ").append(fieldInfo.getProperty());
            }
        }
        s = columns.toString();
        allSelectColumnCache.put(table.getTableName(), s);
        return s;
    }

    /**
     * 指定要查询的属性列 - 这里会自动映射到表字段
     *
     * @param properties
     * @return
     */
    public Example selectProperties(String... properties) {
        if (properties != null && properties.length > 0) {
            if (this.selectColumns == null) {
                this.selectColumns = new HashSet<>();
            }
            for (String property : properties) {
                String column = table.getColumn(property);
                if (column != null && !table.getKeyColumn().equals(column)) {
                    this.selectColumns.add(column);
                }
            }
        }
        return this;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this, table, exists, notNull);
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public String getDynamicTableName() {
        return table.getTableName();
    }

    public static class OrderBy {
        protected TableInfo table;
        protected boolean notNull;
        private Example example;
        private Boolean isProperty;

        public OrderBy(Example example, TableInfo table) {
            this.example = example;
            this.table = table;
        }

        private String property(String property) {
            String column = table.getColumn(property);
            if (column != null) {
                return column;
            } else if (notNull) {
                throw new RuntimeException("Can not find " + property + " property of " + example.entityClass);
            } else {
                return null;
            }
        }

        public OrderBy orderBy(String property) {
            String column = property(property);
            if (column == null) {
                isProperty = false;
                return this;
            }
            if (StringUtils.isNotEmpty(example.getOrderByClause())) {
                example.setOrderByClause(example.getOrderByClause() + "," + column);
            } else {
                example.setOrderByClause(column);
            }
            isProperty = true;
            return this;
        }

        public OrderBy desc() {
            if (isProperty) {
                example.setOrderByClause(example.getOrderByClause() + " DESC");
                isProperty = false;
            }
            return this;
        }

        public OrderBy asc() {
            if (isProperty) {
                example.setOrderByClause(example.getOrderByClause() + " ASC");
                isProperty = false;
            }
            return this;
        }
    }

    protected abstract static class GeneratedCriteria {

        protected Example example;

        protected List<Criterion> criteria;
        //字段是否必须存在
        protected boolean exists;
        //值是否不能为空
        protected boolean notNull;

        protected TableInfo table;

        protected GeneratedCriteria(Example example, TableInfo table, boolean exists, boolean notNull) {
            super();
            this.example = example;
            this.exists = exists;
            this.notNull = notNull;
            criteria = new ArrayList<Criterion>();
            this.table = table;
        }

        private String column(String property) {
            String column = table.getColumn(property);
            if (column != null) {
                return column;
            } else if (exists) {
                throw new RuntimeException("Can not find " + property + " property of " + example.entityClass);
            } else {
                return null;
            }
        }

        private String property(String property) {
            String column = table.getColumn(property);
            if (column != null) {
                return property;
            } else if (exists) {
                throw new RuntimeException("Can not find " + property + " property of " + example.entityClass);
            } else {
                return null;
            }
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            if (condition.startsWith("null")) {
                return;
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                if (notNull) {
                    throw new RuntimeException("Value for " + property + " cannot be null");
                } else {
                    return;
                }
            }
            if (property == null) {
                return;
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                if (notNull) {
                    throw new RuntimeException("Between values for " + property + " cannot be null");
                } else {
                    return;
                }
            }
            if (property == null) {
                return;
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIsNull(String property) {
            addCriterion(column(property) + " is null");
            return (Criteria) this;
        }

        public Criteria andIsNotNull(String property) {
            addCriterion(column(property) + " is not null");
            return (Criteria) this;
        }

        public Criteria andEqualTo(String property, Object value) {
            addCriterion(column(property) + " =", value, property(property));
            return (Criteria) this;
        }

        public Criteria andNotEqualTo(String property, Object value) {
            addCriterion(column(property) + " <>", value, property(property));
            return (Criteria) this;
        }

        public Criteria andGreaterThan(String property, Object value) {
            addCriterion(column(property) + " >", value, property(property));
            return (Criteria) this;
        }

        public Criteria andGreaterThanOrEqualTo(String property, Object value) {
            addCriterion(column(property) + " >=", value, property(property));
            return (Criteria) this;
        }

        public Criteria andLessThan(String property, Object value) {
            addCriterion(column(property) + " <", value, property(property));
            return (Criteria) this;
        }

        public Criteria andLessThanOrEqualTo(String property, Object value) {
            addCriterion(column(property) + " <=", value, property(property));
            return (Criteria) this;
        }

        public Criteria andIn(String property, Collection<?> values) {
            if (values == null || values.isEmpty()) {
                addCriterion("1 <> 1");
            } else {
                addCriterion(column(property) + " in", values, property(property));
            }

            return (Criteria) this;
        }

        public Criteria andNotIn(String property, Collection<?> values) {
            if (values != null && !values.isEmpty()) {
                addCriterion(column(property) + " not in", values, property(property));
            }
            return (Criteria) this;
        }

        public Criteria andBetween(String property, Object value1, Object value2) {
            addCriterion(column(property) + " between", value1, value2, property(property));
            return (Criteria) this;
        }

        public Criteria andNotBetween(String property, Object value1, Object value2) {
            addCriterion(column(property) + " not between", value1, value2, property(property));
            return (Criteria) this;
        }

        public Criteria andLike(String property, String value) {
            addCriterion(column(property) + "  like", value, property(property));
            return (Criteria) this;
        }

        public Criteria andNotLike(String property, String value) {
            addCriterion(column(property) + "  not like", value, property(property));
            return (Criteria) this;
        }


        public Criteria andCondition(String condition) {
            addCriterion(condition);
            return (Criteria) this;
        }


        public Criteria andCondition(String condition, Object value) {
            criteria.add(new Criterion(condition, value));
            return (Criteria) this;
        }

    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria(Example example, TableInfo table, boolean exists, boolean notNull) {
            super(example, table, exists, notNull);
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof Collection<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }
    }
}