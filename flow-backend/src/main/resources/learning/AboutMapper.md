**关于mapper中的相关标签解释**

***sql语句标签***

```sql
-- 查询语句
<select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="java.lang.String" >  
    select  xx from TABLE_NAME 
</select>
         
-- 同理可知：
-- 插入语句(insert)
-- 删除语句(delete)
-- 修改语句(update)

```

标签中相关的属性(以上图select语句为例)
- id="xxx", 表示sql执行语句的唯一标识，也是接口的方法名称，【必须一致才能找到】
- parameterType="xxx", 标识该sql需要传入的参数，类型要与对应的方法一致【可选】
- resultMap="xxx", 定义出参，调用已定义的<resultMap>映射管理器的id值
- resultType="xxx", 定义出参，匹配普通java类型或者是自定义的pojo，【若不指定，将为语句类型默认类型，比如<insert>语句返值为int】

***PS***
```text

对于上面最后一条，至于为何<insert><delete><update>语句的返回值是int，对于JDBC的操作，增删改操作，实际上返回的是操作的条数。
对于Mybatis而言，框架是基于JDBC的，所以此处是沿袭返回值的类型的。

```

**关于取值**

```sql

-- 取值方式 1
#{value jdbcType=valueType}: jdbcType表示该属性类型在数据库中对应的类型

-- 取值方式 2
${value} : 不过这种方式是非常不建议使用的, 可能会发送sql注入而导致安全性的问题。

```


**sql片段标签**