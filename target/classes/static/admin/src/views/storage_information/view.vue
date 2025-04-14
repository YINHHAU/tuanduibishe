<template>
	<el-main class="bg edit_wrap comtable_e">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
		<el-row class="row_ce"> 
							<el-col v-if="user_group === '管理员' || $check_field('get','product_number') || $check_field('add','product_number') || $check_field('set','product_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="商品编号" prop="product_number">
												<el-input id="product_number" v-model="form['product_number']" placeholder="请输入商品编号"
							  v-if="user_group === '管理员' || (form['storage_information_id'] && $check_field('set','product_number')) || (!form['storage_information_id'] && $check_field('add','product_number'))" :disabled="disabledObj['product_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','product_number')">{{form['product_number']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','product_name') || $check_field('add','product_name') || $check_field('set','product_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="商品名称" prop="product_name">
												<el-input id="product_name" v-model="form['product_name']" placeholder="请输入商品名称"
							  v-if="user_group === '管理员' || (form['storage_information_id'] && $check_field('set','product_name')) || (!form['storage_information_id'] && $check_field('add','product_name'))" :disabled="disabledObj['product_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','product_name')">{{form['product_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','product_specifications') || $check_field('add','product_specifications') || $check_field('set','product_specifications')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="商品规格" prop="product_specifications">
												<el-input id="product_specifications" v-model="form['product_specifications']" placeholder="请输入商品规格"
							  v-if="user_group === '管理员' || (form['storage_information_id'] && $check_field('set','product_specifications')) || (!form['storage_information_id'] && $check_field('add','product_specifications'))" :disabled="disabledObj['product_specifications_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','product_specifications')">{{form['product_specifications']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','inventory_quantity') || $check_field('add','inventory_quantity') || $check_field('set','inventory_quantity')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="入库数量" prop="inventory_quantity">
								<el-input-number id="inventory_quantity" v-model.number="form['inventory_quantity']"
						v-if="user_group === '管理员' || (form['storage_information_id'] && $check_field('set','inventory_quantity')) || (!form['storage_information_id'] && $check_field('add','inventory_quantity'))" :disabled="disabledObj['inventory_quantity_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','inventory_quantity')">{{form['inventory_quantity']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','storage_date') || $check_field('add','storage_date') || $check_field('set','storage_date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="入库日期" prop="storage_date">
								<el-date-picker :disabled="disabledObj['storage_date_isDisabled']" v-if="user_group === '管理员' || (form['storage_information_id'] && $check_field('set','storage_date')) || (!form['storage_information_id'] && $check_field('add','storage_date'))" id="storage_date"
						v-model="form['storage_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','storage_date')">{{form['storage_date']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','storage_remarks') || $check_field('add','storage_remarks') || $check_field('set','storage_remarks')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="入库备注" prop="storage_remarks">
								<el-input type="textarea" id="storage_remarks" v-model="form['storage_remarks']" placeholder="请输入入库备注"
						v-if="user_group === '管理员' || (form['storage_information_id'] && $check_field('set','storage_remarks')) || (!form['storage_information_id'] && $check_field('add','storage_remarks'))" :disabled="disabledObj['storage_remarks_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','storage_remarks')">{{form['storage_remarks']}}</div>
							</el-form-item>
			</el-col>
					
	
	
	
	
	
	
		</el-row>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item v-if="$check_action('/storage_information/view','set') || $check_action('/storage_information/view','add') || $check_option('/storage_information/table','examine')">
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
				field: "storage_information_id",
				url_add: "~/api/storage_information/add?",
				url_set: "~/api/storage_information/set?",
				url_get_obj: "~/api/storage_information/get_obj?",
				url_upload: "~/api/storage_information/upload?",

				query: {
					"storage_information_id": 0,
				},

				form: {
								"product_number":  '', // 商品编号
										"product_name":  '', // 商品名称
										"product_specifications":  '', // 商品规格
										"inventory_quantity":  0, // 入库数量
										"storage_date":  '', // 入库日期
										"storage_remarks":  '', // 入库备注
											"storage_information_id": 0, // ID
						
				},
				disabledObj:{
								"product_number_isDisabled": false,
										"product_name_isDisabled": false,
										"product_specifications_isDisabled": false,
					          			"inventory_quantity_isDisabled": false,
										"storage_date_isDisabled": false,
										"storage_remarks_isDisabled": false,
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
													// 获取缓存数据附加
				form = $.db.get("form");
							$.push(this.form ,form);
												
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
										        if (this.form["storage_date"] && this.form["storage_date"].indexOf("-")===-1){
          this.form["storage_date"] = this.$toTime(parseInt(this.form["storage_date"]),"yyyy-MM-dd")
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
																						if(this.form["storage_date"]=="0000-00-00"){
				  this.form["storage_date"] = null;
				}
				if(parseInt(this.form["storage_date"]) > 9999){
					this.form["storage_date"] = this.$toTime(parseInt(this.form["storage_date"]),"yyyy-MM-dd")
				}
							

			},

			/**
			 * 提交前验证事件
			 * @param {Object} 请求参数
			 * @return {String} 验证成功返回null, 失败返回错误提示
			 */
			submit_check(param) {
																																												if (!param.storage_date){
					return "入库日期不能为空";
				}
																						return null;
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/storage_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/storage_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/storage_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/storage_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/storage_information/view','get');
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
