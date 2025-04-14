<template>
	<el-main class="bg edit_wrap comtable_e">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
		<el-row class="row_ce"> 
							<el-col v-if="user_group === '管理员' || $check_field('get','employee_users') || $check_field('add','employee_users') || $check_field('set','employee_users')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="员工用户" prop="employee_users">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_employee_users(form['employee_users']) }}
							<!--<el-input id="business_name" v-model="form['employee_users']" placeholder="请输入员工用户"-->
							<!--v-if="user_group === '管理员' || (form['product_information_id'] && $check_field('set','employee_users')) || (!form['product_information_id'] && $check_field('add','employee_users'))" :disabled="disabledObj['employee_users_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','employee_users')">{{form['employee_users']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['product_information_id'] && $check_field('set','employee_users')) || (!form['product_information_id'] && $check_field('add','employee_users'))" id="employee_users" v-model="form['employee_users']" :disabled="disabledObj['employee_users_isDisabled']">
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
								<el-col v-if="user_group === '管理员' || $check_field('get','product_name') || $check_field('add','product_name') || $check_field('set','product_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="商品名称" prop="product_name">
												<el-input id="product_name" v-model="form['product_name']" placeholder="请输入商品名称"
							  v-if="user_group === '管理员' || (form['product_information_id'] && $check_field('set','product_name')) || (!form['product_information_id'] && $check_field('add','product_name'))" :disabled="disabledObj['product_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','product_name')">{{form['product_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','product_type') || $check_field('add','product_type') || $check_field('set','product_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="商品类型" prop="product_type">
								<el-select id="product_type" v-model="form['product_type']"						v-if="user_group === '管理员' || (form['product_information_id'] && $check_field('set','product_type')) || (!form['product_information_id'] && $check_field('add','product_type'))">
						<el-option v-for="o in list_product_type" :key="o['type_name']" :label="o['type_name']"
							:value="o['type_name']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','product_type')">{{form['product_type']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','commodity_price') || $check_field('add','commodity_price') || $check_field('set','commodity_price')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="商品价格" prop="commodity_price">
								<el-input-number id="commodity_price" v-model.number="form['commodity_price']"
						v-if="user_group === '管理员' || (form['product_information_id'] && $check_field('set','commodity_price')) || (!form['product_information_id'] && $check_field('add','commodity_price'))" :disabled="disabledObj['commodity_price_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','commodity_price')">{{form['commodity_price']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','inventory_quantity') || $check_field('add','inventory_quantity') || $check_field('set','inventory_quantity')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="库存数量" prop="inventory_quantity">
								<el-input-number id="inventory_quantity" v-model.number="form['inventory_quantity']"
						v-if="user_group === '管理员' || (form['product_information_id'] && $check_field('set','inventory_quantity')) || (!form['product_information_id'] && $check_field('add','inventory_quantity'))" :disabled="disabledObj['inventory_quantity_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','inventory_quantity')">{{form['inventory_quantity']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','product_introduction') || $check_field('add','product_introduction') || $check_field('set','product_introduction')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="商品简介" prop="product_introduction">
												<el-input id="product_introduction" v-model="form['product_introduction']" placeholder="请输入商品简介"
							  v-if="user_group === '管理员' || (form['product_information_id'] && $check_field('set','product_introduction')) || (!form['product_information_id'] && $check_field('add','product_introduction'))" :disabled="disabledObj['product_introduction_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','product_introduction')">{{form['product_introduction']}}</div>
											</el-form-item>
			</el-col>
					
	
	
	
	
	
	
		</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item v-if="$check_action('/product_information/view','set') || $check_action('/product_information/view','add') || $check_option('/product_information/table','examine')">
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
				field: "product_information_id",
				url_add: "~/api/product_information/add?",
				url_set: "~/api/product_information/set?",
				url_get_obj: "~/api/product_information/get_obj?",
				url_upload: "~/api/product_information/upload?",

				query: {
					"product_information_id": 0,
				},

				form: {
								"employee_users": 0, // 员工用户
										"product_name":  '', // 商品名称
										"product_type":  '', // 商品类型
										"commodity_price":  0, // 商品价格
										"inventory_quantity":  0, // 库存数量
										"product_introduction":  '', // 商品简介
											"product_information_id": 0, // ID
						
				},
				disabledObj:{
								"employee_users_isDisabled": false,
										"product_name_isDisabled": false,
										"product_type_isDisabled": false,
					          			"commodity_price_isDisabled": false,
					          			"inventory_quantity_isDisabled": false,
										"product_introduction_isDisabled": false,
										},

	
					// 用户列表
				list_user_employee_users: [],
						// 用户组
				group_user_employee_users: "",
				
								// 商品类型选项列表
				list_product_type: [""],
	
		
		
		
	
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
			 * 获取商品类型列表
			 */
			async get_list_product_type() {
				var json = await this.$get("~/api/product_classification/get_list?");
				if(json.result && json.result.list){
					this.list_product_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
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
					bl = this.$check_action('/product_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/product_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/product_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/product_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/product_information/view','get');
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
									this.get_list_product_type();
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
