# 初创公司的痛点

任何一家新兴行业初创公司，从最初的摸爬滚打到小有规模再到站稳脚跟，总是要经历一番自由生长的过程。在迅猛的发展中，各个业务团队不断试错，以业务目标为导向，以快速上线为第一优先级。这个时期的代码，逻辑相对简单，以CRUD为主，经常一个开发单枪独马一个加班一路从页面写到DAO。但经过这样一个快速发展期很多公司都会面临一个技术瓶颈。之前一口气从头写到尾的代码，维护成本越来越高。UI逻辑、业务逻辑、校验逻辑甚至数据访问逻辑都混杂在一起，只要调整页面布局的需求，需要一个开发把代码从UI到业务逻辑到DAO从头到尾都改一遍，然后再让测试好好的回归一把。这样的代码，不仅维护起来成本很高，而且因为没有模块化，单元测试变得难以维护。

而且还有一个问题，之前每个人埋头写的那块业务会导致相同的逻辑再系统的各个角落冗余，如果要对一个业务进行调整，需要有经验的开发整体梳理逻辑，检查风格迥异的代码，最后每个涉及到的逻辑都改一遍需求才能上线。更严重的是，这样修改下来，很容易遗漏某些逻辑，造成线上问题。如果有人员流动，熟悉这块业务的人将越来越少，维护成本会越来越高。这些问题，就是传说中的“技术债”。

每一位有技术背景的老板都知道技术债是一个双刃剑，项目初期，为了业务目标快速上线，必然要牺牲一些可维护性，但是到了后期维护的时候，过于庞大的技术债往往成了业务发展的瓶颈。到了技术债无法承受的时候，旷日持久的重构大法便登上了舞台。

重构不是一见容易的事情，虽然大师们都写过好些书籍指导我们如何重构一个旧系统，但实际上重构总是很难取得期望的效果。

原因有如下几点，首先是成本问题，重构需要花费大量的时间成本，需要将一群开发圈进小黑屋专心重构，重构期间这群开发没有任何的业务贡献，而且重构还容易引入新的Bug。其次是范围问题，要做到一次成功的重构，仅仅像教科书上说的那样把命名不规范的改规范、太长的函数拆分成多个短的函数、把有冗余的代码抽成一个公共方法是远远不够的。一个系统有数百个类成千上万行的代码，时间宝贵，不是每一个几百行的函数都要拆分，难得会改的纯技术代码不一定需要去重构，不是所有有冗余代码都要抽成公共方法，虽然几块代码大同小异，但是某一块后期会有新的动作，就不要把它放到通用的代码块中。最后也是最重要的是业务问题，重构首先要适应业务的发展，代码好看但新需求开发成本增加的重构是错误的。

回到现实的项目中，大师在经典中提到重构前先做好自动化测试，但项目从出生就没有过自动化测试，很多重构因为没有全面的测试，导致很多新问题产生，问题需要人去解决，大量问题爆发以后开发每天被各种问题弄的苦不堪言，很可能就从这个坑跳去下个坑了。说到底，之前都没有人有好的意识，怎么能指望在重构的时候就有突出表现，初创公司初期，往往缺乏资源组建一个强大的技术团队，能有大师来重构系统偿还技术债务是一件非常幸运的事情。

# 如何做到鱼和熊掌兼得

不是每个团队都那么幸运能得到大师的指点，那么在项目开始的时候，就要按照某些规则有意识的避免过重的技术债务。这样虽然类似于带着枷锁跳舞，但好处就像面对灾难前有买了保险，不至于损失惨重。