package com.weweibuy.lds.op.model.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OpLogModuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public OpLogModuleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
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

    public OpLogModuleExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public OpLogModuleExample orderBy(String ... orderByClauses) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < orderByClauses.length; i++) {
            sb.append(orderByClauses[i]);
            if (i < orderByClauses.length - 1) {
                sb.append(" , ");
            }
        }
        this.setOrderByClause(sb.toString());
        return this;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
        rows = null;
        offset = null;
    }

    public static Criteria newAndCreateCriteria() {
        OpLogModuleExample example = new OpLogModuleExample();
        return example.createCriteria();
    }

    public OpLogModuleExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public OpLogModuleExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
        if (condition) {
            then.example(this);
        } else {
            otherwise.example(this);
        }
        return this;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getRows() {
        return this.rows;
    }

    public OpLogModuleExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public OpLogModuleExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public OpLogModuleExample page(Integer page, Integer pageSize) {
        this.offset = page * pageSize;
        this.rows = pageSize;
        return this;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
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
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSystemIdIsNull() {
            addCriterion("system_id is null");
            return (Criteria) this;
        }

        public Criteria andSystemIdIsNotNull() {
            addCriterion("system_id is not null");
            return (Criteria) this;
        }

        public Criteria andSystemIdEqualTo(String value) {
            addCriterion("system_id =", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotEqualTo(String value) {
            addCriterion("system_id <>", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdGreaterThan(String value) {
            addCriterion("system_id >", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdGreaterThanOrEqualTo(String value) {
            addCriterion("system_id >=", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLessThan(String value) {
            addCriterion("system_id <", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLessThanOrEqualTo(String value) {
            addCriterion("system_id <=", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLike(String value) {
            addCriterion("system_id like", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotLike(String value) {
            addCriterion("system_id not like", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdIn(List<String> values) {
            addCriterion("system_id in", values, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotIn(List<String> values) {
            addCriterion("system_id not in", values, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdBetween(String value1, String value2) {
            addCriterion("system_id between", value1, value2, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotBetween(String value1, String value2) {
            addCriterion("system_id not between", value1, value2, "systemId");
            return (Criteria) this;
        }

        public Criteria andMvnArtifactIdIsNull() {
            addCriterion("mvn_artifact_id is null");
            return (Criteria) this;
        }

        public Criteria andMvnArtifactIdIsNotNull() {
            addCriterion("mvn_artifact_id is not null");
            return (Criteria) this;
        }

        public Criteria andMvnArtifactIdEqualTo(String value) {
            addCriterion("mvn_artifact_id =", value, "mvnArtifactId");
            return (Criteria) this;
        }

        public Criteria andMvnArtifactIdNotEqualTo(String value) {
            addCriterion("mvn_artifact_id <>", value, "mvnArtifactId");
            return (Criteria) this;
        }

        public Criteria andMvnArtifactIdGreaterThan(String value) {
            addCriterion("mvn_artifact_id >", value, "mvnArtifactId");
            return (Criteria) this;
        }

        public Criteria andMvnArtifactIdGreaterThanOrEqualTo(String value) {
            addCriterion("mvn_artifact_id >=", value, "mvnArtifactId");
            return (Criteria) this;
        }

        public Criteria andMvnArtifactIdLessThan(String value) {
            addCriterion("mvn_artifact_id <", value, "mvnArtifactId");
            return (Criteria) this;
        }

        public Criteria andMvnArtifactIdLessThanOrEqualTo(String value) {
            addCriterion("mvn_artifact_id <=", value, "mvnArtifactId");
            return (Criteria) this;
        }

        public Criteria andMvnArtifactIdLike(String value) {
            addCriterion("mvn_artifact_id like", value, "mvnArtifactId");
            return (Criteria) this;
        }

        public Criteria andMvnArtifactIdNotLike(String value) {
            addCriterion("mvn_artifact_id not like", value, "mvnArtifactId");
            return (Criteria) this;
        }

        public Criteria andMvnArtifactIdIn(List<String> values) {
            addCriterion("mvn_artifact_id in", values, "mvnArtifactId");
            return (Criteria) this;
        }

        public Criteria andMvnArtifactIdNotIn(List<String> values) {
            addCriterion("mvn_artifact_id not in", values, "mvnArtifactId");
            return (Criteria) this;
        }

        public Criteria andMvnArtifactIdBetween(String value1, String value2) {
            addCriterion("mvn_artifact_id between", value1, value2, "mvnArtifactId");
            return (Criteria) this;
        }

        public Criteria andMvnArtifactIdNotBetween(String value1, String value2) {
            addCriterion("mvn_artifact_id not between", value1, value2, "mvnArtifactId");
            return (Criteria) this;
        }

        public Criteria andMvnVersionIsNull() {
            addCriterion("mvn_version is null");
            return (Criteria) this;
        }

        public Criteria andMvnVersionIsNotNull() {
            addCriterion("mvn_version is not null");
            return (Criteria) this;
        }

        public Criteria andMvnVersionEqualTo(String value) {
            addCriterion("mvn_version =", value, "mvnVersion");
            return (Criteria) this;
        }

        public Criteria andMvnVersionNotEqualTo(String value) {
            addCriterion("mvn_version <>", value, "mvnVersion");
            return (Criteria) this;
        }

        public Criteria andMvnVersionGreaterThan(String value) {
            addCriterion("mvn_version >", value, "mvnVersion");
            return (Criteria) this;
        }

        public Criteria andMvnVersionGreaterThanOrEqualTo(String value) {
            addCriterion("mvn_version >=", value, "mvnVersion");
            return (Criteria) this;
        }

        public Criteria andMvnVersionLessThan(String value) {
            addCriterion("mvn_version <", value, "mvnVersion");
            return (Criteria) this;
        }

        public Criteria andMvnVersionLessThanOrEqualTo(String value) {
            addCriterion("mvn_version <=", value, "mvnVersion");
            return (Criteria) this;
        }

        public Criteria andMvnVersionLike(String value) {
            addCriterion("mvn_version like", value, "mvnVersion");
            return (Criteria) this;
        }

        public Criteria andMvnVersionNotLike(String value) {
            addCriterion("mvn_version not like", value, "mvnVersion");
            return (Criteria) this;
        }

        public Criteria andMvnVersionIn(List<String> values) {
            addCriterion("mvn_version in", values, "mvnVersion");
            return (Criteria) this;
        }

        public Criteria andMvnVersionNotIn(List<String> values) {
            addCriterion("mvn_version not in", values, "mvnVersion");
            return (Criteria) this;
        }

        public Criteria andMvnVersionBetween(String value1, String value2) {
            addCriterion("mvn_version between", value1, value2, "mvnVersion");
            return (Criteria) this;
        }

        public Criteria andMvnVersionNotBetween(String value1, String value2) {
            addCriterion("mvn_version not between", value1, value2, "mvnVersion");
            return (Criteria) this;
        }

        public Criteria andMvnGroupIdIsNull() {
            addCriterion("mvn_group_id is null");
            return (Criteria) this;
        }

        public Criteria andMvnGroupIdIsNotNull() {
            addCriterion("mvn_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andMvnGroupIdEqualTo(String value) {
            addCriterion("mvn_group_id =", value, "mvnGroupId");
            return (Criteria) this;
        }

        public Criteria andMvnGroupIdNotEqualTo(String value) {
            addCriterion("mvn_group_id <>", value, "mvnGroupId");
            return (Criteria) this;
        }

        public Criteria andMvnGroupIdGreaterThan(String value) {
            addCriterion("mvn_group_id >", value, "mvnGroupId");
            return (Criteria) this;
        }

        public Criteria andMvnGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("mvn_group_id >=", value, "mvnGroupId");
            return (Criteria) this;
        }

        public Criteria andMvnGroupIdLessThan(String value) {
            addCriterion("mvn_group_id <", value, "mvnGroupId");
            return (Criteria) this;
        }

        public Criteria andMvnGroupIdLessThanOrEqualTo(String value) {
            addCriterion("mvn_group_id <=", value, "mvnGroupId");
            return (Criteria) this;
        }

        public Criteria andMvnGroupIdLike(String value) {
            addCriterion("mvn_group_id like", value, "mvnGroupId");
            return (Criteria) this;
        }

        public Criteria andMvnGroupIdNotLike(String value) {
            addCriterion("mvn_group_id not like", value, "mvnGroupId");
            return (Criteria) this;
        }

        public Criteria andMvnGroupIdIn(List<String> values) {
            addCriterion("mvn_group_id in", values, "mvnGroupId");
            return (Criteria) this;
        }

        public Criteria andMvnGroupIdNotIn(List<String> values) {
            addCriterion("mvn_group_id not in", values, "mvnGroupId");
            return (Criteria) this;
        }

        public Criteria andMvnGroupIdBetween(String value1, String value2) {
            addCriterion("mvn_group_id between", value1, value2, "mvnGroupId");
            return (Criteria) this;
        }

        public Criteria andMvnGroupIdNotBetween(String value1, String value2) {
            addCriterion("mvn_group_id not between", value1, value2, "mvnGroupId");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(Boolean value) {
            addCriterion("is_delete =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Boolean value) {
            addCriterion("is_delete <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Boolean value) {
            addCriterion("is_delete >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_delete >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Boolean value) {
            addCriterion("is_delete <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_delete <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<Boolean> values) {
            addCriterion("is_delete in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<Boolean> values) {
            addCriterion("is_delete not in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete not between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(LocalDateTime value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(LocalDateTime value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(LocalDateTime value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(LocalDateTime value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<LocalDateTime> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<LocalDateTime> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(LocalDateTime value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(LocalDateTime value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(LocalDateTime value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(LocalDateTime value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<LocalDateTime> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<LocalDateTime> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private OpLogModuleExample example;

        protected Criteria(OpLogModuleExample example) {
            super();
            this.example = example;
        }

        public OpLogModuleExample example() {
            return this.example;
        }

        @Deprecated
        public Criteria andIf(boolean ifAdd, ICriteriaAdd add) {
            if (ifAdd) {
                add.add(this);
            }
            return this;
        }

        public Criteria when(boolean condition, ICriteriaWhen then) {
            if (condition) {
                then.criteria(this);
            }
            return this;
        }

        public Criteria when(boolean condition, ICriteriaWhen then, ICriteriaWhen otherwise) {
            if (condition) {
                then.criteria(this);
            } else {
                otherwise.criteria(this);
            }
            return this;
        }

        @Deprecated
        public interface ICriteriaAdd {
            Criteria add(Criteria add);
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
            if (value instanceof List<?>) {
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
    }

    public interface ICriteriaWhen {
        void criteria(Criteria criteria);
    }

    public interface IExampleWhen {
        void example(com.weweibuy.lds.op.model.example.OpLogModuleExample example);
    }
}