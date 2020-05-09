异步编程


1. 当服务端使用channelPipeline.addLast(new ServerInHandler())时,一定按照ABC123顺序打印
2. 当服务端按照channelPipeline.addLast(businessGroup, new ServerInHandler())时,如果IO线程执行快于业务线程,按照ABC123顺序打印;如果IO线程慢于业务线程,按照123ABC属性打印;




