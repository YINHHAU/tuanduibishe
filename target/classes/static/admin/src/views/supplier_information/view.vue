<template>
	<el-main class="bg edit_wrap comtable_e">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
		<el-row class="row_ce"> 
							<el-col v-if="user_group === '管理员' || $check_field('get','supplier_name') || $check_field('add','supplier_name') || $check_field('set','supplier_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="供应商姓名" prop="supplier_name">
												<el-input id="supplier_name" v-model="form['supplier_name']" placeholder="请输入供应商姓名"
							  v-if="user_group === '管理员' || (form['supplier_information_id'] && $check_field('set','supplier_name')) || (!form['supplier_information_id'] && $check_field('add','supplier_name'))" :disabled="disabledObj['supplier_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','supplier_name')">{{form['supplier_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','mobile_phone_number') || $check_field('add','mobile_phone_number') || $check_field('set','mobile_phone_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="手机号码" prop="mobile_phone_number">
								<el-input id="mobile_phone_number" v-model="form['mobile_phone_number']" placeholder="请输入手机号码" type="tel"
						v-if="user_group === '管理员' || (form['supplier_information_id'] && $check_field('set','mobile_phone_number')) || (!form['supplier_information_id'] && $check_field('add','mobile_phone_number'))">
					</el-input>
					<div v-else-if="$check_field('get','mobile_phone_number')">{{form['mobile_phone_number']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','supply_type') || $check_field('add','supply_type') || $check_field('set','supply_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="供应类型" prop="supply_type">
								<el-select id="supply_type" v-model="form['supply_type']"						v-if="user_group === '管理员' || (form['supplier_information_id'] && $check_field('set','supply_type')) || (!form['supplier_information_id'] && $check_field('add','supply_type'))">
						<el-option v-for="o in list_supply_type" :key="o['type_name']" :label="o['type_name']"
							:value="o['type_name']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','supply_type')">{{form['supply_type']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','supply_of_goods') || $check_field('add','supply_of_goods') || $check_field('set','supply_of_goods')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="供应商品" prop="supply_of_goods">
												<el-input id="supply_of_goods" v-model="form['supply_of_goods']" placeholder="请输入供应商品"
							  v-if="user_group === '管理员' || (form['supplier_information_id'] && $check_field('set','supply_of_goods')) || (!form['supplier_information_id'] && $check_field('add','supply_of_goods'))" :disabled="disabledObj['supply_of_goods_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','supply_of_goods')">{{form['supply_of_goods']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','supply_time') || $check_field('add','supply_time') || $check_field('set','supply_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="供应时间" prop="supply_time">
								<el-date-picker :disabled="disabledObj['supply_time_isDisabled']" v-if="user_group === '管理员' || (form['supplier_information_id'] && $check_field('set','supply_time')) || (!form['supplier_information_id'] && $check_field('add','supply_time'))" id="supply_time"
						v-model="form['supply_time']" type="datetime" placeholder="选择日期时间">
					</el-date-picker>
					<div v-else-if="$check_field('get','supply_time')">{{form['supply_time']}}</div>
							</el-form-item>
			</el-col>
					
	
	
	
	
	
	
		</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item v-if="$check_action('/supplier_information/view','set') || $check_action('/supplier_information/view','add') || $check_option('/supplier_information/table','examine')">
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
				field: "supplier_information_id",
				url_add: "~/api/supplier_information/add?",
				url_set: "~/api/supplier_information/set?",
				url_get_obj: "~/api/supplier_information/get_obj?",
				url_upload: "~/api/supplier_information/upload?",

				query: {
					"supplier_information_id": 0,
				},

				form: {
								"supplier_name":  '', // 供应商姓名
										"mobile_phone_number":  '', // 手机号码
										"supply_type":  '', // 供应类型
										"supply_of_goods":  '', // 供应商品
										"supply_time":  '', // 供应时间
											"supplier_information_id": 0, // ID
						
				},
				disabledObj:{
								"supplier_name_isDisabled": false,
										"mobile_phone_number_isDisabled": false,
										"supply_type_isDisabled": false,
										"supply_of_goods_isDisabled": false,
										"supply_time_isDisabled": false,
										},

	
		
								// 供应类型选项列表
				list_supply_type: [""],
	
		
		
	
			}
		},
		methods: {


	
	
			
	
			
				/**
			 * 获取供应类型列表
			 */
			async get_list_supply_type() {
				var json = await this.$get("~/api/product_classification/get_list?");
				if(json.result && json.result.list){
					this.list_supply_type = json.result.list;
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
									        if (this.form["supply_time"] && this.form["supply_time"].indexOf("-")===-1){
            this.form["supply_time"] = this.$toTime(parseInt(this.form["supply_time"]),"yyyy-MM-dd hh:mm:ss")
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
																					if(this.form["supply_time"]=="0000-00-00 00:00:00"){
				  this.form["supply_time"] = null;
				}
				if(parseInt(this.form["supply_time"]) > 9999){
					this.form["supply_time"] = this.$toTime(parseInt(this.form["supply_time"]),"yyyy-MM-dd hh:mm:ss")
				}
				

			},

			/**
			 * 提交前验证事件
			 * @param {Object} 请求参数
			 * @return {String} 验证成功返回null, 失败返回错误提示
			 */
			submit_check(param) {
																			let mobile_phone_number_phone_regular = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
				if(param.mobile_phone_number && !mobile_phone_number_phone_regular.test(param.mobile_phone_number)){
					return "手机号格式错误"
				}
																															if (!param.supply_time){
					return "供应时间不能为空";
				}
													return null;
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/supplier_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/supplier_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/supplier_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/supplier_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/supplier_information/view','get');
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
								this.get_list_supply_type();
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
