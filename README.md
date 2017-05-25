尚学堂  spring框架课程

1自动装配*********

对象统一由spring管理，可以指定它是多例还是单例子，在需要注入的地方可以指定各种注入方式
首先从大方面就可以区分为xml配置和注解2种方式，然后具体到操作的时候，可以再指定各种属性
bean
 autowire="byType"  默認值为“default” 常用的byName,byType
	 byType只能同类型的有一个bean 否则spring不知道该取哪一个
	 byName spring会按照bean的name值去匹配对象属性的名字
	  我不想单独给每个bean写自动注入怎么办？在beans这个最外层的标签去设置default-autowire="no" 默认是不自动注入
	 

事务的理解*********

tx
	     关于事务：事务的出发点就已经说明了它存在的意义：
	     保证数据的完整性，原子性，一致性 ，隔离性
	     不论从那个角度考虑  再很多场合，尤其是并发操作时，都是很重要和由必须要考虑的事情
	     事务处理也是面向切面编程的一部分
	     

面向切面编程*********


	     面向切面编程包含了很多内容：
	     1权限
	     2效率性能考量
	     3日志输出
	     4事务处理


spring ioc容器  *********

 什么东西交给spring容器，它包纳了那些东西，由一个大管家统一管理
 其实也就给各个对象之间再无形中被组织起来了，也会更加便于管理和之间的通信
 
 通过这次的回顾，很多东西我越发觉得需要上升自己的一个认知层次了
 很多东西不是为了用它而用它，首先要搞懂的是用它来解决什么问题，为什么用它，它比其他的优势在哪里？
会用了，再可以去考究内部的原理，即便不是恨明白，只是要试着去一步步理解。 


jdbc的连接*********

1直接在xml的配置文件里写死连接所需的driver,url，username,password

2写一个jdbc.properties,在spring的配置文件中首先引入该文件，再在需要的地方引入

3原始方式  直接加载驱动类，获取连接  操作db

操作数据库的方式：

	1原生的jdbc连接
	2在xml配置中添加hibernateTemplate模板，在db操作的实现类上注入此属性
	          然后利用hibernateTemplate提供的方法操作数据库
	3 db实现类继承HibernateDaoSupport 注意需要注入sessionFactory,然后久可以继续
	          使用hibernateTemplae提供的方法了
	4直接使用sessionFctory，注入这个bean后，现用现取session，操作数据库
	  总结：
	     
           不管是利用那种方式，前期都是要最好准备好几样东西：
     
      1  数据源  配置好数据源   准备好数据库的连接入口
      2  sessionFacoty 连接工厂提供了一系列的内容，因为这里是和
         hibernate的集成：所以大体要提供的内容有：
            sql 显示和格式化显示输出sql语句
            hibernate需要去关联的db table pojo
                              需要将面向db的几个pojo类告知sessionFactory
                             使用具体那个数据库方言   直接对应数据库  mysql or oracle 等等
     3 hibernateTemplate  对于这个模板的理解：
                 在连接操作数据库的过程中，前期的准备工作和后期的数据库关闭等操作都分离出来，只关心
                 核心对db的crud，这部分内容就是hibernateTempate要处理的
                 它其实就是把这部分内容做了统一管理，即便没有它，有了sessionFacory我们
                 照样可以完成数据库的操作，只不过这个模板给我们都做了封装，不必再由我们从头开始处理了
        

aop动态代理的实现方式：

 何为aop?在目标类方法的前后，环绕式，异常处理式，等等不同情况的织入逻辑代码，对原来的逻辑增强
 那么spring的aop提供了2种实现方式：
 
 参考博客 http://m.blog.csdn.net/article/details?id=49738887
 http://blog.csdn.net/hejingyuan6/article/details/36203505
 1jdk提供的动态代理  实现方式   实现invoke方法***********
 
   利用的是java的反射机制，要求目标类实现统一的接口，在生成类的过程中效率高
 2cglib实现动态代码，利用第三方类库cglib
 
  cglib动态代理底层则是借助asm来实现的
  asm在生成类之后的相关执行过程中比较高效（可以通过将asm生成的类进行缓存，这样解决asm生成类过程低效问题)
 
对动态代理和静态代理的理解********************

 为其它对象提供一个代理类来控制对某个对象的访问
 为了保持行为的一致性，代理类和委托类通常会实现相同的接口，
  所以在访问者看来两者没有丝毫的区别。通过代理类这中间一层，能有效控制对委托类对象的直接访问，也可以很好地隐藏和保护委托类对象，同时也为实施不同控制策略预留了空间，从而在设计上获得了更大的灵活性
 更通俗的说，代理解决的问题当两个类需要通信时，引入第三方代理类，将两个类的关系解耦，让我们只了解代理类即可
 但是切记，代理类和委托类要实现相同的接口，因为代理真正调用的还是委托类的方法。
 
 按照代理的创建时期，代理类可以分为两种： 

静态：由程序员创建代理类或特定工具自动生成源代码再对其编译。
在程序运行前代理类的.class文件就已经存在了。
静态代理类只能为特定的接口(Service)服务。如想要为多个接口服务则需要建立很多个代理类

动态：在程序运行时运用反射机制动态创建而成

 动态代理：在运行时选择合适的实现类
 把代理类和委托类在处理时将二者关联起来，  中间 人  中介
 将2个实际类之间解耦
想办法可以通过一个代理类完成全部的代理功能，那么我们就需要用动态代理
静态代理只能代理一种类型，而且是在编译器就已经确定被代理的对象。
而动态代理是在运行时，通过反射机制实现动态代理，并且能够代理各种类型的对象
jdk动态代理类只能代理接口（不支持抽象类），
代理类都需要实现InvocationHandler类，实现invoke方法。
该invoke方法就是调用被代理接口的所有方法时需要调用的，
该invoke方法返回的值是被代理接口的一个实现类

如果我们把对外的接口都通过动态代理来实现，
那么所有的函数调用最终都会经过invoke函数的转发，
因此我们就可以在这里做一些自己想做的操作，
比如日志系统、事务、拦截器、权限控制等。
这也就是AOP(面向切面编程)的基本原理
AOP（AspectOrientedProgramming）：
将日志记录，性能统计，安全控制，事务处理，异常处理等代码从业务逻辑代码中划分出来，
通过对这些行为的分离，我们希望可以将它们独立到非指导业务逻辑的方法中，
进而改变这些行为的时候不影响业务逻辑的代码---解耦

动态代理与静态代理相比较，最大的好处是接口中声明的所有方法都被转移到调用处理器一个集中的方法中处理（InvocationHandler.invoke）。
这样，在接口方法数量比较多的时候，我们可以进行灵活处理，
而不需要像静态代理那样每一个方法进行中转。
而且动态代理的应用使我们的类职责更加单一，复用性更强

代理对象就是把被代理对象包装一层，在其内部做一些额外的工作，比如用户需要上facebook,而普通网络无法直接访问，网络代理帮助用户先翻墙，然后再访问facebook。这就是代理的作用了。


纵观静态代理与动态代理，它们都能实现相同的功能，而我们看从静态代理到动态代理的这个过程，我们会发现其实动态代理只是对类做了进一步抽象和封装，使其复用性和易用性得到进一步提升而这不仅仅符合了面向对象的设计理念，其中还有AOP的身影，这也提供给我们对类抽象的一种参考。
关于动态代理与AOP的关系，AOP是一种思想 ,动态代理是一种AOP思想的实现！

 <context:annotation-config />
 <!-- 
此方式是通过注解的形式向需要王bean属性中注入内容
通过此注解，就不需要在这里定义bean时给bean加上auto-wire属性设置值了
这个注解，其实是实例化了4个XXXPostProcessor，是用来在bean实例化时去检查是否有添加注解配置
如果有就调用这里实例化的处理器bean，来对注解做出解释处理，注入需要的属性
 -->
 
常见的错误********************
	
	同类型的bean定义了多个，而且注入方式又是按照类型自动注入：
	
	UnsatisfiedDependencyException: Error creating bean with name 'userService' defined in class path resource [beans.xml]: Unsatisfied dependency expressed through bean property 'userDAO': : No unique bean of type [com.bjsxt.dao.UserDAO] is defined: expected single matching bean but found 2: [userDAO, userDAO2]; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No unique bean of type [com.bjsxt.dao.UserDAO] is defined: expected single matching bean but found 2: [userDAO, userDAO2]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireByType(AbstractAutowireCapableBeanFactory.java:1091)
	生命周期 lifecycle

        没有指定xml文件的位置ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans1.xml");
    
    IllegalStateException: BeanFactory not initialized or already closed - call 'refresh' before accessing beans via the ApplicationContext
	  
	  添加对应的构造方法，区别于上面的set方法注入   ，注入如果是set方法注入时，bean的初始化时需要又无candelabra构造方法的
     Error creating bean with name 'userService' defined in class path resource [beans.xml]: 1 constructor arguments specified but no matching constructor found in bean 'userService' (hint: specify index and/or type arguments for simple parameters to avoid type ambiguities)

    applicationContext.destroy(); 为什么需要手动关闭容器？ 关于bean的生命周期问题
    
		 * 对于这里非web方式启动的spring加载配置文件，要想停止应用上下文，需要手动调用它的destroy方法
		 * 如果bean指定scope=prototype,则不会调用destroy方法，没有监听  单例可以
		 * bean加载的时机是可以指定的：
		 *   默认是随着加载配置文件，容器初始化的时刻一起将其下的bean实例化
		 *   也可以通过属性lazy-init来指定当调用ctx.getBean("dd")的时刻即用的时候才去实例化
		 *   这样就不会在一开始等容器启动就花费好多时间在实例化众多的bean上
		 *   
		 *   多个bean都有init-method,destroy-method时：
		 *  	daoimpl init
				service init
				user saved!
				service destroy
				daoimpl destroy
		 */
		
		配置文件引入某种xsd，但是在识别上总是时好时坏，不知所谓。。。		
        org.springframework.beans.factory.xml.XmlBeanDefinitionStoreException: Line 28 in XML document from class path resource [beans_xml.xml] is invalid; nested exception is org.xml.sax.SAXParseException; lineNumber: 28; columnNumber: 66; cvc-complex-type.2.4.a: 发现了以元素 'aop:pointcut' 开头的无效内容。应以 '{"http://www.springframework.org/schema/aop":aspect}' 之一开头。

关于表和类的映射**********
        
        /**
		 * 注意：这里在执行 db操作时，会考虑到实体和表之间的映射关系
		 * 在实体类上必须注解上@Entity 主键上@Id这样的标识
		 * 如果db中主键是需要人为指定的，就不能再插入操作时在表id列加上@generateValue这样的标识
		 * 因为这本身就是矛盾的，如果给了这个自生成的主键标识，数据库又没指定autoincrement，这样就会报错：
		 * 
		 */
		 
		 
		 not give a default value
		 数据库和表之间没有在主键自增上又一个统一的处理，一方没有设置，获取是人为设置和自增的相互矛盾也会导致报错为此
		 
		 
		 
如何让我的hibernate能orm方式去操作db呢？	***********

这里直接指定和下面的扫描是一个目的，只是方式不同	
    <!--方式一-->
	<property name="annotatedClasses">
	<!--这里配置注解类，@Entity等，和db table 匹配->
		<list>
			<value>com.bjsxt.model.User</value>
		</list>
	</property>
	<!--方式二-->	
	<property name="packagesToScan">
			<list>
				<value>com.bjsxt.model</value>
				
			</list>
	</property>
		
		
还是存在一些问题的，代码没有整理，但意思到了，着就好比是老师教了一个算法，面对无穷的加减法
题目是永远是做不完的，我也只是把这写东西简单的罗列在了这里
其实也还是又些啰嗦的，其实是因为我在要在一个项目里掺杂着做了好几个测试，在某些方面又相互影响了
我也不想再去做整理，简化成一种后，可能会丢掉一些过程，所以就这样保留在这里了
这里也只是对知识点做了一个简单的整理
方便以后查看
其实又很多隐藏的东西，自己没有深入了解