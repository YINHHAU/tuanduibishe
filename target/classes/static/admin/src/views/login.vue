<template>
	<article class="sign_in login_conter">
		<div class="warp">
			<el-form :model="form" :rules="rules" ref="form" label-width="60px" class="form">
				<h1>校园无人车智能库存与调拨子系统</h1>

				<el-form-item label="账号" prop="account">
					<el-input type="text" v-model="form.account" placeholder="请输入账号">
					</el-input>
				</el-form-item>

				<el-form-item label="密码" prop="password">
					<el-input type="password" v-model="form.password" placeholder="请输入密码" autocomplete="off">
					</el-input>
				</el-form-item>
				<div class="btns">
					<el-button class="float-right  lg_login" type="primary" @click="sign_in()">登录</el-button>

					<el-button class="float-right  lg_register" @click="$router.push('./register')">注册</el-button>
					<el-button class="float-right  lg_forgot" @click="$router.push('./forgot')">忘记密码?</el-button>
				</div>
			</el-form>
		</div>
	</article>
</template>

<script>
import { rsaEncrypt } from "@/store/encrypt";

export default {
	data: function () {
		return {
			oauth: {
				signIn: false
			},
			web: this.$store.state.web,
			form: {
				account: "",
				password: "",
			},
			remember_me: 1,
			rules: {
				account: [{
					required: true,
					message: '请输入账号',
					trigger: 'blur'
				},
				{
					min: 5,
					max: 16,
					message: '长度在 5 到 16 个字符',
					trigger: 'blur'
				}
				],
				password: [{
					required: true,
					message: '请输入密码',
					trigger: 'blur'
				},
				{
					min: 5,
					max: 16,
					message: '长度在 5 到 16 个字符',
					trigger: 'blur'
				}
				]
			},
		}
	},
	methods: {
		sign_in() {
			var f = this.form;
			var form = {
				password: rsaEncrypt(f.password)
			};
			var account = f.account + '';

			// 账号类型判断
			// if (account.indexOf('@') !== -1) {
			// 	form.email = account;
			// } else if (/1[0-9]{10}/.test(account)) {
			// 	form.phone = account;
			// } else {
			// 	form.username = account;
			// }

			form.username = account;

			if (!this.form.account) {
				return this.$message.warning("请输入正确账号")
			} else if (!this.form.password) {
				return this.$message.warning("请输入正确密码")
			}
			var _this = this;

			// 查询用户
			this.$post('~/api/user/login?', form, (res) => {
				if (res.result && res.result.obj) {
					var obj = res.result.obj;

					// 缓存用户
					_this.$store.commit('user_set', obj);

					// 存储用户
					if (_this.remember_me) {
						$.db.set('account', account);
					}

					// 获取权限
					_this.$get_auth(obj.user_group, () => {
						var url = _this.$redirect();
						_this.$router.push(url || '/');
					})

					// 存储登录回来的用户信息
					$.db.set('user_group', JSON.stringify(res.result.obj));


				} else if (res.error) {
					console.log(res.error);
					_this.$toast(res.error.message, 'error');
				}
			});
		},
	},
	created() {

	},
	components: {
	}
}
</script>

<style>
.sign_in {
	box-sizing: border-box;
	height: 100vh;
	padding: .5rem;
	width: 100%;
	background: url(../../public/img/bg.jpg) no-repeat;
	background-size: cover;
	color: #fff;
}

.form,
.form h1 {
	width: 80%;
	color: #fff;
	background-color: rgba(0, 0, 0, 0.5);
}
</style>
