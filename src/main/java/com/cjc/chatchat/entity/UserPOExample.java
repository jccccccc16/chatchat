package com.cjc.chatchat.entity;

import java.util.ArrayList;
import java.util.List;

public class UserPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserPOExample() {
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

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andLoginAcctIsNull() {
            addCriterion("login_acct is null");
            return (Criteria) this;
        }

        public Criteria andLoginAcctIsNotNull() {
            addCriterion("login_acct is not null");
            return (Criteria) this;
        }

        public Criteria andLoginAcctEqualTo(String value) {
            addCriterion("login_acct =", value, "loginAcct");
            return (Criteria) this;
        }

        public Criteria andLoginAcctNotEqualTo(String value) {
            addCriterion("login_acct <>", value, "loginAcct");
            return (Criteria) this;
        }

        public Criteria andLoginAcctGreaterThan(String value) {
            addCriterion("login_acct >", value, "loginAcct");
            return (Criteria) this;
        }

        public Criteria andLoginAcctGreaterThanOrEqualTo(String value) {
            addCriterion("login_acct >=", value, "loginAcct");
            return (Criteria) this;
        }

        public Criteria andLoginAcctLessThan(String value) {
            addCriterion("login_acct <", value, "loginAcct");
            return (Criteria) this;
        }

        public Criteria andLoginAcctLessThanOrEqualTo(String value) {
            addCriterion("login_acct <=", value, "loginAcct");
            return (Criteria) this;
        }

        public Criteria andLoginAcctLike(String value) {
            addCriterion("login_acct like", value, "loginAcct");
            return (Criteria) this;
        }

        public Criteria andLoginAcctNotLike(String value) {
            addCriterion("login_acct not like", value, "loginAcct");
            return (Criteria) this;
        }

        public Criteria andLoginAcctIn(List<String> values) {
            addCriterion("login_acct in", values, "loginAcct");
            return (Criteria) this;
        }

        public Criteria andLoginAcctNotIn(List<String> values) {
            addCriterion("login_acct not in", values, "loginAcct");
            return (Criteria) this;
        }

        public Criteria andLoginAcctBetween(String value1, String value2) {
            addCriterion("login_acct between", value1, value2, "loginAcct");
            return (Criteria) this;
        }

        public Criteria andLoginAcctNotBetween(String value1, String value2) {
            addCriterion("login_acct not between", value1, value2, "loginAcct");
            return (Criteria) this;
        }

        public Criteria andUserPswdIsNull() {
            addCriterion("user_pswd is null");
            return (Criteria) this;
        }

        public Criteria andUserPswdIsNotNull() {
            addCriterion("user_pswd is not null");
            return (Criteria) this;
        }

        public Criteria andUserPswdEqualTo(String value) {
            addCriterion("user_pswd =", value, "userPswd");
            return (Criteria) this;
        }

        public Criteria andUserPswdNotEqualTo(String value) {
            addCriterion("user_pswd <>", value, "userPswd");
            return (Criteria) this;
        }

        public Criteria andUserPswdGreaterThan(String value) {
            addCriterion("user_pswd >", value, "userPswd");
            return (Criteria) this;
        }

        public Criteria andUserPswdGreaterThanOrEqualTo(String value) {
            addCriterion("user_pswd >=", value, "userPswd");
            return (Criteria) this;
        }

        public Criteria andUserPswdLessThan(String value) {
            addCriterion("user_pswd <", value, "userPswd");
            return (Criteria) this;
        }

        public Criteria andUserPswdLessThanOrEqualTo(String value) {
            addCriterion("user_pswd <=", value, "userPswd");
            return (Criteria) this;
        }

        public Criteria andUserPswdLike(String value) {
            addCriterion("user_pswd like", value, "userPswd");
            return (Criteria) this;
        }

        public Criteria andUserPswdNotLike(String value) {
            addCriterion("user_pswd not like", value, "userPswd");
            return (Criteria) this;
        }

        public Criteria andUserPswdIn(List<String> values) {
            addCriterion("user_pswd in", values, "userPswd");
            return (Criteria) this;
        }

        public Criteria andUserPswdNotIn(List<String> values) {
            addCriterion("user_pswd not in", values, "userPswd");
            return (Criteria) this;
        }

        public Criteria andUserPswdBetween(String value1, String value2) {
            addCriterion("user_pswd between", value1, value2, "userPswd");
            return (Criteria) this;
        }

        public Criteria andUserPswdNotBetween(String value1, String value2) {
            addCriterion("user_pswd not between", value1, value2, "userPswd");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(String value) {
            addCriterion("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(String value) {
            addCriterion("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(String value) {
            addCriterion("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(String value) {
            addCriterion("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(String value) {
            addCriterion("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(String value) {
            addCriterion("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLike(String value) {
            addCriterion("birthday like", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotLike(String value) {
            addCriterion("birthday not like", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<String> values) {
            addCriterion("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<String> values) {
            addCriterion("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(String value1, String value2) {
            addCriterion("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(String value1, String value2) {
            addCriterion("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andHeaderPicturePathIsNull() {
            addCriterion("header_picture_path is null");
            return (Criteria) this;
        }

        public Criteria andHeaderPicturePathIsNotNull() {
            addCriterion("header_picture_path is not null");
            return (Criteria) this;
        }

        public Criteria andHeaderPicturePathEqualTo(String value) {
            addCriterion("header_picture_path =", value, "headerPicturePath");
            return (Criteria) this;
        }

        public Criteria andHeaderPicturePathNotEqualTo(String value) {
            addCriterion("header_picture_path <>", value, "headerPicturePath");
            return (Criteria) this;
        }

        public Criteria andHeaderPicturePathGreaterThan(String value) {
            addCriterion("header_picture_path >", value, "headerPicturePath");
            return (Criteria) this;
        }

        public Criteria andHeaderPicturePathGreaterThanOrEqualTo(String value) {
            addCriterion("header_picture_path >=", value, "headerPicturePath");
            return (Criteria) this;
        }

        public Criteria andHeaderPicturePathLessThan(String value) {
            addCriterion("header_picture_path <", value, "headerPicturePath");
            return (Criteria) this;
        }

        public Criteria andHeaderPicturePathLessThanOrEqualTo(String value) {
            addCriterion("header_picture_path <=", value, "headerPicturePath");
            return (Criteria) this;
        }

        public Criteria andHeaderPicturePathLike(String value) {
            addCriterion("header_picture_path like", value, "headerPicturePath");
            return (Criteria) this;
        }

        public Criteria andHeaderPicturePathNotLike(String value) {
            addCriterion("header_picture_path not like", value, "headerPicturePath");
            return (Criteria) this;
        }

        public Criteria andHeaderPicturePathIn(List<String> values) {
            addCriterion("header_picture_path in", values, "headerPicturePath");
            return (Criteria) this;
        }

        public Criteria andHeaderPicturePathNotIn(List<String> values) {
            addCriterion("header_picture_path not in", values, "headerPicturePath");
            return (Criteria) this;
        }

        public Criteria andHeaderPicturePathBetween(String value1, String value2) {
            addCriterion("header_picture_path between", value1, value2, "headerPicturePath");
            return (Criteria) this;
        }

        public Criteria andHeaderPicturePathNotBetween(String value1, String value2) {
            addCriterion("header_picture_path not between", value1, value2, "headerPicturePath");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
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
}