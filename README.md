backstreettoy
=============
这个项目的目标是，通过提供一系列的工具包和Tricks来降低普通GRUD网站的学习和开发成本并提高生产效率。

Our goal is reducing the costs and increasing the productity for full-interactive GURD website development by providing a series of tools and tricky methods.

目前已经实现的工具
------------
一个Servlet Http包装工具，解析Http的Request请求并将参数封装成对象传给内部的业务实现，业务实现完成业务逻辑后，返回Json对象，包装类转化成Json String返回给浏览器。

首先将实现类与Web容器提高业务实现的可测性。用各种Mock工具对Servlet相关参数Mock手感均不是很爽。

其次，从交互角度上看，Ajax请求的灵活度比页面同步提交的灵活性高，特别在如今Web页面越来越花哨，各种异步提交局部刷新等，尽管需要多写Javascript逻辑，但是相比带来的好处（灵活的控制流程、Javascript前台渲染降低后台压力与延迟等），个人看是值得的。而且需要额外多写的Javascript主要是在Ajax完成后的渲染逻辑，这一块是一直可以优化的。


正在计划中的工具
-------------
容器计算操作工具。Java中容器的重要性无需置疑，从《实现模式》作者在第九章强调其重要性到Google的Guava包到Java8的Stream支持均可以看出。有一套完整的容器计算框架支持可以极大的提高开发效率并且降低出错的概率
