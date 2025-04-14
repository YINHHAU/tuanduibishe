<template>
	<div>
			<el-form-item v-if="$check_register_field('add','user_name','/employee_users/view')" label="用户姓名" prop="user_name">
				<el-input id="user_name" v-model="form['user_name']" placeholder="请输入用户姓名"></el-input>
				</el-form-item>
				<el-form-item v-if="$check_register_field('add','user_gender','/employee_users/view')" label="用户性别" prop="user_gender">
				<el-select id="user_gender" v-model="form['user_gender']">
			<el-option v-for="o in list_user_gender" :key="o" :label="o" :value="o">
			</el-option>
		</el-select>
				</el-form-item>
				<el-form-item v-if="$check_register_field('add','mobile_phone_number','/employee_users/view')" label="手机号码" prop="mobile_phone_number">
				<el-input id="mobile_phone_number" v-model="form['mobile_phone_number']" placeholder="请输入手机号码" type="tel">
		</el-input>
				</el-form-item>
	
	</div>
</template>

<script>
	import mixin from "@/mixins/component.js";

	export default {
		mixins: [mixin],
		props:{

			form:{
				type: Object,
				default: function(){
					return {
							"user_name":  '' ,
								"user_gender":  '' ,
								"mobile_phone_number":  '' ,
						}
				}
			},

		},
		data() {
			return {
				field: "employee_users_id",
				url_add: "~/api/employee_users/add?",
				url_set: "~/api/employee_users/set?",
				url_upload: "~/api/employee_users/upload?",

								list_user_gender: ['男','女'],
						rules: {
					"user_name": [    {required: true,message: '用户姓名不能为空'},  ],
					"user_gender": [    {required: true,message: '用户性别不能为空'},  ],
					"mobile_phone_number": [   {validator: this.$is_phone,trigger: 'blur'},   {required: true,message: '手机号码不能为空'},  ],
				}

			}
		},
		methods: {

	
	
	

	
	
	

	
	
	

		},
		created() {
					}
	}
</script>
