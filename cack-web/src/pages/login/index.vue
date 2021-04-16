<template>
  <div class="sign-in">
    <main class="main-container">
      <section class="auth-sidebar">
        <div class="auth-art-image"></div>
      </section>
      <section class="content">
        <header class="auth-header">
          <h1 class="logo-text">Ca<span class="logo-ck">ck</span></h1>
        </header>
        <div class="auth-main">
          <h2>登录</h2>
          <form class="auth-form">
            <div class="form-field">
              <label for="username">用户名</label>
              <input
                type="text"
                name="username"
                placeholder="用户名"
                maxlength="20"
                tabindex="1"
                v-model="formState.username"
              />
            </div>
            <div class="form-field">
              <label for="password">密码</label>
              <input
                type="password"
                name="password"
                placeholder="密码"
                maxlength="20"
                tabindex="2"
                v-model="formState.password"
              />
            </div>
            <input
              type="submit"
              class="form-submit"
              value="登录"
              tabindex="3"
              @click.prevent="onSubmit"
            />
          </form>
        </div>
      </section>
    </main>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { message, notification } from 'ant-design-vue';
import store from '../../store';
import {
  USERNAME_CANNOT_EMPTY,
  PASSWORD_CANNOT_EMPTY
} from '../../utils/constant';

const router = useRouter();

const formState = ref({
  username: 'admin',
  password: '123456'
});

// 登录
const onSubmit = () => {
  const { username, password } = formState.value;
  if (username.length === 0) {
    message.error(USERNAME_CANNOT_EMPTY);
  } else if (password.length === 0) {
    message.error(PASSWORD_CANNOT_EMPTY);
  } else {
    store.dispatch('Login', { username, password }).then(result => {
      router.push({
        path: '/',
        replace: true
      });

      notification.open({
        message: '👏 欢迎来到 Cack ',
        description: `${result.nickname}，欢迎回来！`
      });
    });
  }
};
</script>

<style scoped>
.sign-in {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #f0f7fd;
}

.main-container {
  display: flex;
  max-width: 800px;
  height: 480px;
  border-radius: 16px;
  background: #fff;
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.12);
}

.auth-sidebar {
  display: flex;
  align-items: center;
  width: 300px;
  border-top-left-radius: 16px;
  border-bottom-left-radius: 16px;
  background: #f1cdd7;
}

.auth-art-image {
  width: 300px;
  height: 300px;
  background-image: url('../../assets/sign-in.jpg');
  background-size: 100%;
}

.content {
  width: 500px;
  padding: 24px;
}

.auth-header {
  text-align: right;
}

.auth-main {
  max-width: 396px;
  margin: 0 auto;
  margin-top: 64px;
}

label {
  display: block;
  margin: 14px 0 4px;
  font-size: 15px;
  font-weight: bold;
  line-height: 24px;
}

input[type='text'],
input[type='password'] {
  box-sizing: border-box;
  width: 100%;
  height: 40px;
  padding: 10px 16px;
  margin-bottom: 2px;
  line-height: 24px;
  border: 1px solid transparent;
  outline: none;
  border-radius: 8px;
  background-color: rgb(239, 242, 247);
  transition: background-color 200ms ease, outline 200ms ease, color 200ms ease,
    box-shadow 200ms ease;
  appearance: none;
}

input[type='text']:hover,
input[type='password']:hover {
  border-color: rgba(0, 0, 0, 0.2);
  box-shadow: 0 0 0 4px rgba(0, 117, 255, 0.16);
  background: #fff;
}

input[type='text']:focus,
input[type='password']:focus {
  border-color: rgba(0, 117, 255, 0.6);
  box-shadow: 0 0 0 4px rgba(0, 117, 255, 0.16);
  background-color: #fff;
}

input[type='text']:active,
input[type='password']:active {
  border-color: rgba(0, 0, 0, 0.2);
  box-shadow: none;
  background: #fff;
}

input::-webkit-input-placeholder {
  color: #acb1bc;
}

.form-submit {
  box-sizing: border-box;
  display: block;
  width: 100%;
  height: 40px;
  padding: 10px 16px;
  margin-top: 22px;
  color: #fff;
  font-size: 15px;
  font-weight: 500px;
  line-height: 20px;
  border-radius: 8px;
  text-align: center;
  background-color: #0075ff;
  transition: color 200ms ease;
  cursor: pointer;
  appearance: none;
  border: none;
  outline: none;
}

.form-submit:hover,
.form-submit:focus {
  background-color: #0075ffcc;
}

@media (max-width: 800px) {
  .sign-in {
    background: #fff;
  }

  .main-container {
    width: 100%;
    height: 100%;
    box-shadow: none;
    background: transparent;
  }

  .auth-sidebar {
    display: none;
  }

  .content {
    width: 100%;
  }
}
</style>
