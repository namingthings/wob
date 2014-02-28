Using with Java 8 will produce problems:

HttpRequest(GET,http://localhost:8080/web/index.mustache,List(Connection: Keep-Alive, Host: localhost:8080, User-Agent: Wget/1.13.4 (linux-gnueabihf)),Empty,HTTP/1.1)
org.fusesource.scalate.CompilerException: Compilation failed:
error: error while loading CharSequence, class file '/opt/jdk1.8.0/jre/lib/rt.jar(java/lang/CharSequence.class)' is broken
(class java.lang.RuntimeException/bad constant pool tag 18 at byte 10)
one error found

	at org.fusesource.scalate.support.ScalaCompiler.compile(ScalaCompiler.scala:105)
	at org.fusesource.scalate.TemplateEngine.compileAndLoad(TemplateEngine.scala:757)
	at org.fusesource.scalate.TemplateEngine.compileAndLoadEntry(TemplateEngine.scala:699)
	at org.fusesource.scalate.TemplateEngine.liftedTree1$1(TemplateEngine.scala:419)
	at org.fusesource.scalate.TemplateEngine.load(TemplateEngine.scala:413)
	at org.fusesource.scalate.TemplateEngine.load(TemplateEngine.scala:471)
	at org.fusesource.scalate.TemplateEngine.layout(TemplateEngine.scala:573)
	at wob.ScalateSupport$$anonfun$render$1.apply(ScalateSupport.scala:29)
	at wob.ScalateSupport$$anonfun$render$1.apply(ScalateSupport.scala:29)
	at spray.routing.directives.RouteDirectives$$anonfun$complete$1$$anon$3.apply(RouteDirectives.scala:50)
	at spray.routing.directives.RouteDirectives$$anonfun$complete$1$$anon$3.apply(RouteDirectives.scala:49)
	at spray.routing.directives.BasicDirectives$$anonfun$mapRequestContext$1$$anonfun$apply$1.apply(BasicDirectives.scala:30)
	at spray.routing.directives.BasicDirectives$$anonfun$mapRequestContext$1$$anonfun$apply$1.apply(BasicDirectives.scala:30)
	at spray.routing.directives.BasicDirectives$$anonfun$mapRequestContext$1$$anonfun$apply$1.apply(BasicDirectives.scala:30)
	at spray.routing.directives.BasicDirectives$$anonfun$mapRequestCo