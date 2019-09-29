# Java Exception Handling

## Basic Exception Handling in Java

		Exception propagation  -  异常传播
		Throwing Exceptions    -  抛出异常
		try-catch-finally            -  异常处理语句
			try
			catch Exceptions   -  捕获异常
			catch Multiple Exceptions
			finally Exceptions        -   即使出现异常，一定要执行
		How to release resources after an exception  - 异常以后如何释放资源 ？
			1. Try-Catch-Finally     使用finally强制释放资源
			2. Try-with-resources    try调用 **java.lang.AutoCloseable** 接口来实现释放
		Catch or Propagate Exceptions?   - 异常即使处理还是向上传播异常？
		example


## java exception  - java异常机制

		异常概念
		try-catch-finally运行机制
			语法规则
			执行顺序
		Java存在的异常
			Throwable类
			Java常见异常
				运行时异常类及子类 runtimeException
				非运行时异常类及子类
		设计自己异常类
