/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月28日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.api.common;

/**
 * Description：<br> 
 * TODO
 * @author  Yu.Zhang
 * @date    2015年12月28日
 * @version v1.0.0
 */
public interface ApiCode {
	
	/**
	 * 客户端应当继续发送请求。这个临时响应是用来通知客户端它的部分 请求已经被服务器接收，且仍未被拒绝。
	 * 客户端应当继续发送请求的 剩余部分，或者如果请求已经完成，忽略这个响应。
	 * 服务器必须在请 求完成后向客户端发送一个最终响应
	 */
	public static String HTTP_CODE_100 = "100";
	
	/**
	 * 服务器已经理解了客户端的请求，并将通过 Upgrade 消息头通知客户 端采用不同的协议来完成这个请求。
	 * 在发送完这个响应最后的空行后， 服务器将会切换到在 Upgrade 消息头中定义的那些协议。  
	 * 只有在切换新的协议更有好处的时候才应该采取类似措施。
	 * 例如，切 换到新的 HTTP 版本比旧版本更有优势，或者切换到一个实时且同步 的协议以传送利用此类特性的资源。 
	 */
	public static String HTTP_CODE_101 = "101";
	
	/**
	 * 由 WebDAV（RFC 2518）扩展的状态码，代表处理将被继续执行
	 */
	public static String HTTP_CODE_102 = "102";
	
	/**
	 * 请求已成功，请求所希望的响应头或数据体将随此响应返回
	 */
	public static String HTTP_CODE_200 = "200";
	
	/**
	 * 请求已经被实现，而且有一个新的资源已经依据请求的需要而建立， 且其 URI 已经随 Location 头信息返回。
	 * 假如需要的资源无法及时建 立的话，应当返回 '202 Accepted'。 
	 */
	public static String HTTP_CODE_201 = "201";
	
	/**
	 *服务器已接受请求，但尚未处理。正如它可能被拒绝一样，最终该请 求可能会也可能不会被执行。
	 *在异步操作的场合下，没有比发送这个 状态码更方便的做法了。   
	 *返回 202 状态码的响应的目的是允许服务器接受其他过程的请求（例 如某个每天只执行一次的基于批处理的操作），
	 *而不必让客户端一直保 持与服务器的连接直到批处理操作全部完成。
	 *在接受请求处理并返回 202 状态码的响应应当在返回的实体中包含一些指示处理当前状态的 信息，
	 *以及指向处理状态监视器或状态预测的指针，以便用户能够估 计操作是否已经完成
	 */
	public static String HTTP_CODE_202 = "202";
	
	/**
	 * 服务器已成功处理了请求，但返回的实体头部元信息不是在原始服务 器上有效的确定集合，而是来自本地或者第三方的拷贝。
	 * 当前的信息 可能是原始版本的子集或者超集。例如，包含资源的元数据可能导致 原始服务器知道元信息的超级。
	 * 使用此状态码不是必须的，而且只有 在响应不使用此状态码便会返回 200 OK 的情况下才是合适的
	 */
	public static String HTTP_CODE_203 = "203";
	
	/**
	 * 服务器成功处理了请求，但不需要返回任何实体内容，并且希望返回 更新了的元信息。
	 * 响应可能通过实体头部的形式，返回新的或更新后 的元信息。
	 * 如果存在这些头部信息，则应当与所请求的变量相呼应。 
	 * 如果客户端是浏览器的话，那么用户浏览器应保留发送了该请求的页 面，
	 * 而不产生任何文档视图上的变化，即使按照规范新的或更新后的 元信息应当被应用到用户浏览器活动视图中的文档。   
	 * 由于 204 响应被禁止包含任何消息体，因此它始终以消息头后的第一 个空行结尾。 
	 */
	public static String HTTP_CODE_204 = "204";
	
	/**
	 * 服务器成功处理了请求，且没有返回任何内容。
	 * 但是与 204 响应不同， 返回此状态码的响应要求请求者重置文档视图。
	 * 该响应主要是被用于 接受用户输入后，立即重置表单，以便用户能够轻松地开始另一次输 入。    
	 * 与 204 响应一样，该响应也被禁止包含任何消息体，且以消息头后的 第一个空行结束
	 */
	public static String HTTP_CODE_205 = "205";
	
	/**
	 * 服务器已经成功处理了部分 GET 请求。
	 */
	public static String HTTP_CODE_206 = "206";
	
	/**
	 * 由 WebDAV(RFC 2518)扩展的状态码，代表之后的消息体将是一个 XML 消息，并且可能依照之前子请求数量的不同，包含一系列独立的 响应代码。 
	 */
	public static String HTTP_CODE_207 = "207";
	
	/**
	 * 被请求的资源有一系列可供选择的回馈信息，每个都有自己特定的地 址和浏览器驱动的商议信息。
	 * 用户或浏览器能够自行选择一个首选的 地址进行重定向。  
	 */
	public static String HTTP_CODE_300 = "300";
	
	/**
	 * 被请求的资源已永久移动到新位置，并且将来任何对此资源的引用都 应该使用本响应返回的若干个 URI 之一
	 */
	public static String HTTP_CODE_301 = "301";
	
	/**
	 * 请求的资源现在临时从不同的 URI 响应请求。由于这样的重定向是 临时的，客户端应当继续向原有地址发送以后的请求。
	 * 只有在 Cache-Control 或 Expires 中进行了指定的情况下，这个响应才是可缓 存的。
	 * 新的临时性的 URI 应当在响应的 Location 域中返回。
	 * 除非这 是一个 HEAD 请求，否则响应的实体中应当包含指向新的 URI 的超 链接及简短说明。
	 */
	public static String HTTP_CODE_302 = "302";
	
	/**
	 * 对应当前请求的响应可以在另一个 URI 上被找到，而且客户端应当 采用 GET 的方式访问那个资源。
	 * 这个方法的存在主要是为了允许由 脚本激活的POST请求输出重定向到一个新的资源。
	 * 这个新的 URI 不 是原始资源的替代引用。
	 */
	public static String HTTP_CODE_303 = "303";
	
	/**
	 * 如果客户端发送了一个带条件的 GET 请求且该请求已被允许，
	 * 而文 档的内容（自上次访问以来或者根据请求的条件）并没有改变，
	 * 则服 务器应当返回这个状态码。
	 * 304 响应禁止包含消息体，因此始终以消 息头后的第一个空行结尾。 
	 */
	public static String HTTP_CODE_304 = "304";
	
	/**
	 * 被请求的资源必须通过指定的代理才能被访问。
	 * Location 域中将给出 指定的代理所在的 URI 信息，
	 * 接收者需要重复发送一个单独的请求， 
	 * 通过这个代理才能访问相应资源。
	 * 只有原始服务器才能建立 305 响应
	 */
	public static String HTTP_CODE_305  = "305";
	
	/**
	 * 请求的资源现在临时从不同的 URI 响应请求。由于这样的重定向是临 时的，客户端应当继续向原有地址发送以后的请求。 
	 */
	public static String HTTP_CODE_307 = "307";
	
	/**
	 * 1、语义有误，当前请求无法被服务器理解。除非进行修改，否则客户 端不应该重复提交这个请求。    
	 * 2、请求参数有误。
	 */
	public static String HTTP_CODE_400 = "400";
	
	/**
	 * 当前请求需要用户验证。
	 * 该响应必须包含一个适用于被请求资源的 WWW-Authenticate 信息头用以询问用户信息。
	 * 客户端可以重复提交 一个包含恰当的 Authorization 头信息的请求。
	 * 如果当前请求已经包 含了 Authorization 证书，那么 401 响应代表着服务器验证已经拒绝 了那些证书。
	 * 如果 401 响应包含了与前一个响应相同的身份验证询问， 且浏览器已经至少尝试了一次验证，
	 * 那么浏览器应当向用户展示响应 中包含的实体信息，因为这个实体信息中可能包含了相关诊断信息。 
	 */
	public static String HTTP_CODE_401 = "401";
	
	/**
	 * 该状态码是为了将来可能的需求而预留的
	 */
	public static String HTTP_CODE_402 = "402";
	
	/**
	 * 服务器已经理解请求，但是拒绝执行它。
	 * 与 401 响应不同的是，身份 验证并不能提供任何帮助，而且这个请求也不应该被重复提交。
	 * 如果 这不是一个 HEAD 请求，而且服务器希望能够讲清楚为何请求不能 被执行，那么就应该在实体内描述拒绝的原因。
	 * 当然服务器也可以返 回一个 404 响应，假如它不希望让客户端获得任何信息。
	 */
	public static String HTTP_CODE_403 = "403";
	
	/**
	 * 请求失败，请求所希望得到的资源未被在服务器上发现。
	 * 没有信息能 够告诉用户这个状况到底是暂时的还是永久的。
	 * 假如服务器知道情况 的话，应当使用 410 状态码来告知旧资源因为某些内部的配置机制问 题，
	 * 已经永久的不可用，而且没有任何可以跳转的地址。
	 * 404 这个状 态码被广泛应用于当服务器不想揭示到底为何请求被拒绝或者没有其 他适合的响应可用的情况下。
	 */
	public static String HTTP_CODE_404 = "404 ";
	
	/**
	 * 请求行中指定的请求方法不能被用于请求相应的资源。
	 * 该响应必须返 回一个 Allow 头信息用以表示出当前资源能够接受的请求方法的列 表。 
	 * 鉴于 PUT，DELETE 方法会对服务器上的资源进行写操作，
	 * 因而绝 大部分的网页服务器都不支持或者在默认配置下不允许上述请求方 法，对于此类请求均会返回 405 错误。
	 */
	public static String HTTP_CODE_405 = "405";
	
	/**
	 * 请求的资源的内容特性无法满足请求头中的条件，因而无法生成响应 实体。   除非这是一个 HEAD 请求，
	 * 否则该响应就应当返回一个包含可以让 用户或者浏览器从中选择最合适的实体特性以及地址列表的实体。
	 * 实 体的格式由 Content-Type 头中定义的媒体类型决定。浏览器可以根据 格式及自身能力自行作出最佳选择。
	 * 但是，规范中并没有定义任何作 出此类自动选择的标准。  
	 */
	public static String HTTP_CODE_406 = "406";
	
	/**
	 * 与 401 响应类似，只不过客户端必须在代理服务器上进行身份验证。 
	 * 代理服务器必须返回一个 Proxy-Authenticate 用以进行身份询问。客 户端可以返回一个 Proxy-Authorization 信息头用以验证。 
	 */
	public static String HTTP_CODE_407 = "407";
	
	/**
	 *请求超时。
	 *客户端没有在服务器预备等待的时间内完成一个请求的发 送。客户端可以随时再次提交这一请求而无需进行任何更改
	 */
	public static String HTTP_CODE_408 = "408";
	
	/**
	 * 由于和被请求的资源的当前状态之间存在冲突，请求无法完成。
	 * 这个 代码只允许用在这样的情况下才能被使用：用户被认为能够解决冲突， 
	 * 并且会重新提交新的请求。该响应应当包含足够的信息以便用户发现 冲突的源头。    
	 * 冲突通常发生于对 PUT 请求的处理中。
	 * 例如，在采用版本检查的环 境下，某次 PUT 提交的对特定资源的修改请求所附带的版本信息与 之前的某个（第三方）请求向冲突，
	 * 那么此时服务器就应该返回一个 409 错误，告知用户请求无法完成。
	 * 此时，响应实体中很可能会包含 两个冲突版本之间的差异比较，以便用户重新提交归并以后的新版本。 
	 */
	public static String HTTP_CODE_409 = "409";
	
	/**
	 * 被请求的资源在服务器上已经不再可用，而且没有任何已知的转发地 址。 
	 */
	public static String HTTP_CODE_410 = "410";
	
	/**
	 *服务器拒绝在没有定义 Content-Length 头的情况下接受请求。
	 *在添加 了表明请求消息体长度的有效 Content-Length 头之后，客户端可以再 次提交该请求
	 */
	public static String HTTP_CODE_411 = "411";
	
	/**
	 * 服务器遇到了一个未曾预料的状况，导致了它无法完成对请求的处理。
	 *  一般来说，这个问题都会在服务器的程序码出错时出现。 
	 */
	public static String HTTP_CODE_500 = "500";
	
	/**
	 * 服务器不支持当前请求所需要的某个功能。
	 * 当服务器无法识别请求的 方法，并且无法支持其对任何资源的请求。 
	 */
	public static String HTTP_CODE_501 = "501";
	
	/**
	 * 作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收 到无效的响应。  
	 */
	public static String HTTP_CODE_502 = "502";
	
	/**
	 * 由于临时的服务器维护或者过载，服务器当前无法处理请求。
	 * 这个状 况是临时的，并且将在一段时间以后恢复。
	 * 如果能够预计延迟时间， 那么响应中可以包含一个 Retry-After 头用以标明这个延迟时间。
	 * 如 果没有给出这个 Retry-After 信息，那么客户端应当以处理 500 响应 的方式处理它
	 */
	public static String HTTP_CODE_503 = "503";
	
	/**
	 * 作为网关或者代理工作的服务器尝试执行请求时，
	 * 未能及时从上游服 务器（URI 标识出的服务器，例如 HTTP、FTP、LDAP）或者辅助服 务器（例如 DNS）收到响应。 
	 */
	public static String HTTP_CODE_504 = "504";
	
	/**
	 * 服务器不支持，或者拒绝支持在请求中使用的 HTTP 版本。
	 * 这暗示着 服务器不能或不愿使用与客户端相同的版本。
	 * 响应中应当包含一个描 述了为何版本不被支持以及服务器支持哪些协议的实体。 
	 */
	public static String HTTP_CODE_505 = "505";
	
	/**
	 * 服务端处理结果失败
	 */
	public static String HTTP_CODE_900 = "900";
	
	/**
	 * 接口版本已无效，需要使作其它版本的接口 
	 */
	public static String HTTP_CODE_901 = "901";
	
	/**
	 * 该版本的接口不存在 
	 */
	public static String HTTP_CODE_902 = "902";
	
	/**
	 * 接口调用方应用未注册 
	 */
	public static String HTTP_CODE_903 = "903";
	
	/**
	 * 接口调用方用户身份验证失败 
	 */
	public static String HTTP_CODE_904 = "904";
	
	/**
	 * 接口调用方 TOKEN 已失效 
	 */
	public static String HTTP_CODE_905 = "905";
	
	/** 领取红包：红包不够 */
	public static String HTTP_CODE_601 = "601";
	
	/** 红包活动：未领取 */
	public static String HTTP_CODE_701 = "701";
	/** 红包活动：已领取 */
	public static String HTTP_CODE_702 = "702";
	/** 红包活动：抢光了 */
	public static String HTTP_CODE_703 = "703";
	/** 红包活动：已结束 */
	public static String HTTP_CODE_704 = "704";
	/** 红包活动：未开始 */
	public static String HTTP_CODE_705 = "705";
	
	
	/** 加息劵活动：未领取 */
	public static String HTTP_CODE_801 = "801";
	/** 加息劵活动：已领取 */
	public static String HTTP_CODE_802 = "802";
	/** 加息劵活动：抢光了 */
	public static String HTTP_CODE_803 = "803";
	/** 加息劵活动：已结束 */
	public static String HTTP_CODE_804 = "804";
	/** 加息劵活动：未开始 */
	public static String HTTP_CODE_805 = "805";
	
}

