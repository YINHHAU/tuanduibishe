<template>
	<el-main class="bg edit_wrap comtable_e">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
		<el-row class="row_ce"> 
							<el-col v-if="user_group === '管理员' || $check_field('get','employee_users') || $check_field('add','employee_users') || $check_field('set','employee_users')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="员工用户" prop="employee_users">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_employee_users(form['employee_users']) }}
							<!--<el-input id="business_name" v-model="form['employee_users']" placeholder="请输入员工用户"-->
							<!--v-if="user_group === '管理员' || (form['scrap_information_id'] && $check_field('set','employee_users')) || (!form['scrap_information_id'] && $check_field('add','employee_users'))" :disabled="disabledObj['employee_users_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','employee_users')">{{form['employee_users']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['scrap_information_id'] && $check_field('set','employee_users')) || (!form['scrap_information_id'] && $check_field('add','employee_users'))" id="employee_users" v-model="form['employee_users']" :disabled="disabledObj['employee_users_isDisabled']">
								<el-option v-for="o in list_user_employee_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','employee_users')" id="employee_users" v-model="form['employee_users']" :disabled="true">
								<el-option v-for="o in list_user_employee_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="employee_users" v-model="form['employee_users']" :disabled="disabledObj['employee_users_isDisabled']">
							<el-option v-for="o in list_user_employee_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','vehicle_model') || $check_field('add','vehicle_model') || $check_field('set','vehicle_model')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="车辆型号" prop="vehicle_model">
												<el-input id="vehicle_model" v-model="form['vehicle_model']" placeholder="请输入车辆型号"
							  v-if="user_group === '管理员' || (form['scrap_information_id'] && $check_field('set','vehicle_model')) || (!form['scrap_information_id'] && $check_field('add','vehicle_model'))" :disabled="disabledObj['vehicle_model_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','vehicle_model')">{{form['vehicle_model']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','engine_number') || $check_field('add','engine_number') || $check_field('set','engine_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发动机号" prop="engine_number">
												<el-input id="engine_number" v-model="form['engine_number']" placeholder="请输入发动机号"
							  v-if="user_group === '管理员' || (form['scrap_information_id'] && $check_field('set','engine_number')) || (!form['scrap_information_id'] && $check_field('add','engine_number'))" :disabled="disabledObj['engine_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','engine_number')">{{form['engine_number']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','vehicle_frame_number') || $check_field('add','vehicle_frame_number') || $check_field('set','vehicle_frame_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="车辆架号" prop="vehicle_frame_number">
												<el-input id="vehicle_frame_number" v-model="form['vehicle_frame_number']" placeholder="请输入车辆架号"
							  v-if="user_group === '管理员' || (form['scrap_information_id'] && $check_field('set','vehicle_frame_number')) || (!form['scrap_information_id'] && $check_field('add','vehicle_frame_number'))" :disabled="disabledObj['vehicle_frame_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','vehicle_frame_number')">{{form['vehicle_frame_number']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','vehicle_license_plate') || $check_field('add','vehicle_license_plate') || $check_field('set','vehicle_license_plate')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="车辆车牌" prop="vehicle_license_plate">
												<el-input id="vehicle_license_plate" v-model="form['vehicle_license_plate']" placeholder="请输入车辆车牌"
							  v-if="user_group === '管理员' || (form['scrap_information_id'] && $check_field('set','vehicle_license_plate')) || (!form['scrap_information_id'] && $check_field('add','vehicle_license_plate'))" :disabled="disabledObj['vehicle_license_plate_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','vehicle_license_plate')">{{form['vehicle_license_plate']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','registration_date') || $check_field('add','registration_date') || $check_field('set','registration_date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="登记日期" prop="registration_date">
								<el-date-picker :disabled="disabledObj['registration_date_isDisabled']" v-if="user_group === '管理员' || (form['scrap_information_id'] && $check_field('set','registration_date')) || (!form['scrap_information_id'] && $check_field('add','registration_date'))" id="registration_date"
						v-model="form['registration_date']" type="datetime" placeholder="选择日期时间">
					</el-date-picker>
					<div v-else-if="$check_field('get','registration_date')">{{form['registration_date']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','note_details') || $check_field('add','note_details') || $check_field('set','note_details')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="备注详情" prop="note_details">
								<el-input type="textarea" id="note_details" v-model="form['note_details']" placeholder="请输入备注详情"
						v-if="user_group === '管理员' || (form['scrap_information_id'] && $check_field('set','note_details')) || (!form['scrap_information_id'] && $check_field('add','note_details'))" :disabled="disabledObj['note_details_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','note_details')">{{form['note_details']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','scrap_date') || $check_field('add','scrap_date') || $check_field('set','scrap_date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="报废日期" prop="scrap_date">
								<el-date-picker :disabled="disabledObj['scrap_date_isDisabled']" v-if="user_group === '管理员' || (form['scrap_information_id'] && $check_field('set','scrap_date')) || (!form['scrap_information_id'] && $check_field('add','scrap_date'))" id="scrap_date"
						v-model="form['scrap_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','scrap_date')">{{form['scrap_date']}}</div>
							</el-form-item>
			</el-col>
					
	
	
	
	
	
	
		</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item v-if="$check_action('/scrap_information/view','set') || $check_action('/scrap_information/view','add') || $check_option('/scrap_information/table','examine')">
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
				field: "scrap_information_id",
				url_add: "~/api/scrap_information/add?",
				url_set: "~/api/scrap_information/set?",
				url_get_obj: "~/api/scrap_information/get_obj?",
				url_upload: "~/api/scrap_information/upload?",

				query: {
					"scrap_information_id": 0,
				},

				form: {
								"employee_users": 0, // 员工用户
										"vehicle_model":  '', // 车辆型号
										"engine_number":  '', // 发动机号
										"vehicle_frame_number":  '', // 车辆架号
										"vehicle_license_plate":  '', // 车辆车牌
										"registration_date":  '', // 登记日期
										"note_details":  '', // 备注详情
										"scrap_date":  '', // 报废日期
											"scrap_information_id": 0, // ID
						
				},
				disabledObj:{
								"employee_users_isDisabled": false,
										"vehicle_model_isDisabled": false,
										"engine_number_isDisabled": false,
										"vehicle_frame_number_isDisabled": false,
										"vehicle_license_plate_isDisabled": false,
										"registration_date_isDisabled": false,
										"note_details_isDisabled": false,
										"scrap_date_isDisabled": false,
										},

	
					// 用户列表
				list_user_employee_users: [],
						// 用户组
				group_user_employee_users: "",
				
		
		
		
		
		
		
	
			}
		},
		methods: {


	
	
				/**
			 * 获取员工用户用户列表
			 */
			async get_list_user_employee_users() {
                // if(this.user_group !== "管理员" && this.form["employee_users"] === 0) {
                //     this.form["employee_users"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=员工用户");
                if(json.result && json.result.list){
                    this.list_user_employee_users = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					/**
			 * 获取员工用户用户组
			 */
			async get_group_user_employee_users() {
							this.form["employee_users"] = this.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=员工用户");
				if(json.result && json.result.obj){
					this.group_user_employee_users = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_employee_users(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_employee_users.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
									for (let key in _this.form) {
							arrForm.push(key)
						}
												_this.form["employee_users"] = id
									_this.disabledObj['employee_users' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "employee_users") {
			                      _this.form[arrForm[j]] = res.result.obj[arr[i]]
			                      _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								  break;
								} else {
								  _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								}
							  }
							}
						  }
						}
					}
				});
			},
					get_user_employee_users(id){
				var obj = this.list_user_employee_users.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			
	
			
	
			
	
			
	
			
	
			
	
			
	
		
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
											        if (this.form["registration_date"] && this.form["registration_date"].indexOf("-")===-1){
            this.form["registration_date"] = this.$toTime(parseInt(this.form["registration_date"]),"yyyy-MM-dd hh:mm:ss")
        }
						        if (this.form["scrap_date"] && this.form["scrap_date"].indexOf("-")===-1){
          this.form["scrap_date"] = this.$toTime(parseInt(this.form["scrap_date"]),"yyyy-MM-dd")
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
																									if(this.form["registration_date"]=="0000-00-00 00:00:00"){
				  this.form["registration_date"] = null;
				}
				if(parseInt(this.form["registration_date"]) > 9999){
					this.form["registration_date"] = this.$toTime(parseInt(this.form["registration_date"]),"yyyy-MM-dd hh:mm:ss")
				}
														if(this.form["scrap_date"]=="0000-00-00"){
				  this.form["scrap_date"] = null;
				}
				if(parseInt(this.form["scrap_date"]) > 9999){
					this.form["scrap_date"] = this.$toTime(parseInt(this.form["scrap_date"]),"yyyy-MM-dd")
				}
			

			},

			/**
			 * 提交前验证事件
			 * @param {Object} 请求参数
			 * @return {String} 验证成功返回null, 失败返回错误提示
			 */
			submit_check(param) {
																																																					if (!param.registration_date){
					return "登记日期不能为空";
				}
																								if (!param.scrap_date){
					return "报废日期不能为空";
				}
													return null;
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/scrap_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/scrap_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/scrap_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/scrap_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/scrap_information/view','get');
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
					this.get_list_user_employee_users();
					this.get_group_user_employee_users();
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
