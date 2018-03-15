#我们的痛点
任何一家新兴创业公司，从最初的摸爬滚打到小有规模再到站稳脚跟，总是要经理一番自由生长的过程，在迅猛的快速发展中，各个业务团队不断试错，以业务目标为导向，以快速上线为第一优先级。这个时期的代码，逻辑相对简单，以CRUD为主，并且一个人从页面入参一路写到DAO层。

经历了快速发展期后，很多公司都会面临一个阵痛期，之前一口气从头写到尾的代码，维护起来成本实在太高。UI逻辑、业务逻辑、校验逻辑甚至数据访问逻辑，都混杂在一起，稍微调整一下页面布局，又要一个开发把一个类从页面到DAO从头到尾改一遍。这样的代码，不仅维护起来成本很高，而且因为没有模块化，单元测试变得难以维护。

而且，之前每个人埋头写自己的那块业务导致很多代码实现都有冗余，如果有需要要对一个业务进行调整，需要有经验的开发梳理逻辑，检查代码，然后修改好几处实现，需求才能上线。更严重的是，这样修改下来，还很容易遗漏某些逻辑，造成线上问题。