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

通过该标签可以定义能复用的sql语句片段，在执行sql语句标签中直接引用即可，提高编码效率，简化代码，提高可读性

```sql

-- 定义sql片段
<sql id="orderAndItem">  
o.order_id,o.cid,o.address,o.create_date,o.orderitem_id,i.orderitem_id,i.product_id,i.count  
</sql>

-- 具体使用如下
<select id="findOrderAndItemsByOid" parameterType="java.lang.String" resultMap="BaseResultMap">
select
        <!--引用sql片段-->  
        <include refid="orderAndItem" />
from ordertable o
    join orderitem i on o.orderitem_id = i.orderitem_id
where o.order_id = #{orderId}
    </select>

```

**映射管理器resultMap**

映射管理器是mybatis中最强大的工具，可以进行实体类之间的联系，并管理结果和实体类之间的关系。
1： 需要配置属性：

```sql

<resultMap id="" type="">

```
id 表示这个映射管理器的唯一标识， 外部通过该值引用。
type: 表示需要映射的实体类

2： 需要配置的参数

```sql

<id column = " " property= " " />
<result column= " " property=" " />

```
<id> 标签指的是：结果集中唯一的列[column]和实体属性【property】的映射关系。需要注意的是<id>标签管理的列未必是主键列，更具具体的需要指定。
<result>标签指的是：结果集中普通列[column]和实体属性[property]的映射关系。

3：需要维护的关系

所谓关系维护是值在主表查询时将其关联的字表的结果也查询出来

  - 一对一关系<assocation property="" javatype=""> property="" 被维护实体在宿主实体中的属性名， javaType="" 维护实体的类型

```java

public class Orderitem {
    private String orderitemId;
    private String productId;
    private Integer count;
    private Product product;
}

```
从上面代码可以看出，Product对象在OrderItem实体中以product属性存在
在对应的mapper.xml中

```xml
<resultMap id="BaseResultMap" type="pojo.Orderitem" >  
    <id column="orderitem_id" property="orderitemId" jdbcType="VARCHAR" />  
    <result column="product_id" property="productId" jdbcType="VARCHAR" />  
    <result column="count" property="count" jdbcType="INTEGER" />  
<!-- 通过association 维护 一对一关系 -->  
<association property="product" javaType="pojo.Product">  
    <id column="product_id" property="productId"/>  
    <result column="product_factroy" property="productFactroy"/>  
    <result column="product_store" property="productStore"/>  
    <result column="product_descript" property="productDescript"/>  
    </association>  
</resultMap>  
```
从xml配型信息中可以看出，在resultMap管理器中，通过<assocaiation>进行了维护，也就是说在查询OrderItem对象时，可以把关联的Product对象的
信息也查询出来。
  - 一对多的维护关系

































