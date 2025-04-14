<template>
	<el-main class="bg edit_wrap comtable_e">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
		<el-row class="row_ce"> 
							<el-col v-if="user_group === '管理员' || $check_field('get','model_name') || $check_field('add','model_name') || $check_field('set','model_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="车型名称" prop="model_name">
												<el-input id="model_name" v-model="form['model_name']" placeholder="请输入车型名称"
							  v-if="user_group === '管理员' || (form['unmanned_vehicle_type_id'] && $check_field('set','model_name')) || (!form['unmanned_vehicle_type_id'] && $check_field('add','model_name'))" :disabled="disabledObj['model_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','model_name')">{{form['model_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','vehicle_parameters') || $check_field('add','vehicle_parameters') || $check_field('set','vehicle_parameters')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="车辆参数" prop="vehicle_parameters">
												<el-input id="vehicle_parameters" v-model="form['vehicle_parameters']" placeholder="请输入车辆参数"
							  v-if="user_group === '管理员' || (form['unmanned_vehicle_type_id'] && $check_field('set','vehicle_parameters')) || (!form['unmanned_vehicle_type_id'] && $check_field('add','vehicle_parameters'))" :disabled="disabledObj['vehicle_parameters_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','vehicle_parameters')">{{form['vehicle_parameters']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','maximum_vehicle_speed') || $check_field('add','maximum_vehicle_speed') || $check_field('set','maximum_vehicle_speed')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="最大车速" prop="maximum_vehicle_speed">
								<el-input-number id="maximum_vehicle_speed" v-model.number="form['maximum_vehicle_speed']"
						v-if="user_group === '管理员' || (form['unmanned_vehicle_type_id'] && $check_field('set','maximum_vehicle_speed')) || (!form['unmanned_vehicle_type_id'] && $check_field('add','maximum_vehicle_speed'))" :disabled="disabledObj['maximum_vehicle_speed_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','maximum_vehicle_speed')">{{form['maximum_vehicle_speed']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','security_parameter') || $check_field('add','security_parameter') || $check_field('set','security_parameter')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="安全参数" prop="security_parameter">
												<el-input id="security_parameter" v-model="form['security_parameter']" placeholder="请输入安全参数"
							  v-if="user_group === '管理员' || (form['unmanned_vehicle_type_id'] && $check_field('set','security_parameter')) || (!form['unmanned_vehicle_type_id'] && $check_field('add','security_parameter'))" :disabled="disabledObj['security_parameter_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','security_parameter')">{{form['security_parameter']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','drive_parameters') || $check_field('add','drive_parameters') || $check_field('set','drive_parameters')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="驱动参数" prop="drive_parameters">
												<el-input id="drive_parameters" v-model="form['drive_parameters']" placeholder="请输入驱动参数"
							  v-if="user_group === '管理员' || (form['unmanned_vehicle_type_id'] && $check_field('set','drive_parameters')) || (!form['unmanned_vehicle_type_id'] && $check_field('add','drive_parameters'))" :disabled="disabledObj['drive_parameters_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','drive_parameters')">{{form['drive_parameters']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','networking_system') || $check_field('add','networking_system') || $check_field('set','networking_system')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="联网系统" prop="networking_system">
												<el-input id="networking_system" v-model="form['networking_system']" placeholder="请输入联网系统"
							  v-if="user_group === '管理员' || (form['unmanned_vehicle_type_id'] && $check_field('set','networking_system')) || (!form['unmanned_vehicle_type_id'] && $check_field('add','networking_system'))" :disabled="disabledObj['networking_system_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','networking_system')">{{form['networking_system']}}</div>
											</el-form-item>
			</el-col>
					
	
	
	
	
	
	
		</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item v-if="$check_action('/unmanned_vehicle_type/view','set') || $check_action('/unmanned_vehicle_type/view','add') || $check_option('/unmanned_vehicle_type/table','examine')">
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
				<el-form-item v-else>
					<el-button @click="cancel()">返回</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "unmanned_vehicle_type_id",
				url_add: "~/api/unmanned_vehicle_type/add?",
				url_set: "~/api/unmanned_vehicle_type/set?",
				url_get_obj: "~/api/unmanned_vehicle_type/get_obj?",
				url_upload: "~/api/unmanned_vehicle_type/upload?",

				query: {
					"unmanned_vehicle_type_id": 0,
				},

				form: {
								"model_name":  '', // 车型名称
										"vehicle_parameters":  '', // 车辆参数
										"maximum_vehicle_speed":  0, // 最大车速
										"security_parameter":  '', // 安全参数
										"drive_parameters":  '', // 驱动参数
										"networking_system":  '', // 联网系统
											"unmanned_vehicle_type_id": 0, // ID
						
				},
				disabledObj:{
								"model_name_isDisabled": false,
										"vehicle_parameters_isDisabled": false,
					          			"maximum_vehicle_speed_isDisabled": false,
										"security_parameter_isDisabled": false,
										"drive_parameters_isDisabled": false,
										"networking_system_isDisabled": false,
										},

	
		
		
		
		
		
	
			}
		},
		methods: {


	
	
			
	
			
	
			
	
			
	
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
																
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
																$.db.del("form");

				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
																								

			},

			/**
			 * 提交前验证事件
			 * @param {Object} 请求参数
			 * @return {String} 验证成功返回null, 失败返回错误提示
			 */
			submit_check(param) {
																																																												return null;
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/unmanned_vehicle_type/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/unmanned_vehicle_type/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/unmanned_vehicle_type/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/unmanned_vehicle_type/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/unmanned_vehicle_type/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
														},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}




</style>
