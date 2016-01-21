backstreettoy
=============
这个项目的目标是，通过提供一系列的工具包和Tricks来降低普通GRUD网站的学习和开发成本并提高生产效率。

Our goal is reducing the costs and increasing the productity for full-interactive GURD website development by providing a series of tools and tricky methods.

目前包括的工具为：一个Servlet Http包装工具，解析Http的Request请求并将参数封装成对象传给内部的业务实现，业务实现完成业务逻辑后，返回Json对象，包装类转化成Json String返回给浏览器。提高业务实现的可测性。从交互角度上看，Ajax请求的灵活度比页面同步提交的灵活性高，尽管需要多写Javascript逻辑，个人看是值得的。
