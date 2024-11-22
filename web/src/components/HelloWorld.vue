<template>
  <div>
    <h2>Chat Room</h2>

    <!-- 用户注册 -->
    <div v-if="!registered">
      <input v-model="userId" placeholder="Enter your user ID"/>
      <button @click="register">Register</button>
    </div>

    <!-- 显示聊天信息 -->
    <div v-else>
      <ul>
        <li v-for="(msg, index) in messages" :key="index">{{ msg }}</li>
      </ul>

      <!-- 发送消息 -->
      <input v-model="inputMsg" placeholder="Type a message" @keyup.enter="sendMessage"/>
      <input v-model="targetUser" placeholder="Enter target user ID (for private messages, leave empty for all)"/>
      <button @click="sendMessage">Send</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      socket: null,
      userId: '',
      inputMsg: '',
      targetUser: '',
      messages: [],
      registered: false
    };
  },
  methods: {
    // 连接 WebSocket
    connect() {
      this.socket = new WebSocket("ws://localhost:8199/ws");

      // 处理来自服务器的消息
      this.socket.onmessage = (event) => {
        this.messages.push(event.data);
      };

      // 处理 WebSocket 连接关闭
      this.socket.onclose = (event) => {
        console.log("Connection closed", event);
      };

      // 处理 WebSocket 连接打开
      this.socket.onopen = () => {
        console.log("Connected to WebSocket server");
      };
    },

    // 注册用户
    register() {
      if (!this.userId.trim()) {
        alert('Please enter a valid user ID');
        return;
      }

      // 发送注册消息
      const registerMessage = {
        type: 'register',
        userId: this.userId
      };
      this.socket.send(JSON.stringify(registerMessage));
      this.registered = true;
    },

    // 发送消息
    sendMessage() {
      if (!this.inputMsg.trim()) {
        alert('Message cannot be empty');
        return;
      }

      const message = {
        type: 'message',
        userId: this.userId,
        to: this.targetUser || 'all', // 如果 targetUser 为空，则广播消息
        content: this.inputMsg
      };
      this.socket.send(JSON.stringify(message));

      // 清空输入框
      this.inputMsg = '';
    }
  },
  mounted() {
    this.connect();
  }
};
</script>
