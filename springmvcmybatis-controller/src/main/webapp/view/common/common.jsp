<script type="text/javascript" src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
<script type="text/javascript" src="http://cdn.goeasy.io/goeasy.js"></script>
<script type="text/javascript" src="resource/js/common/cookieTool.js"></script>
<script type="text/javascript">



    // 第三方goEasy实现后台与web页面消息互通
    var goEasy = new GoEasy({
        appkey: '256648b7-adc4-4235-b994-3c543898ad54'

    });

    var userId = getCookie("user_id");
    /**
     * 订阅所有消息
     */
    goEasy.subscribe({
        channel: 'channel_all',
        onMessage: function(message){
            alert(message.content);
        }
    });

    /**
     * 订阅当前用户的消息
     */
    goEasy.subscribe({
        channel: "channel_"+userId,
        onMessage: function(message){
            alert(message.content);
        }
    });
</script>
