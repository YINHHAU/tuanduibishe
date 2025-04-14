<template>
	<div class="page_root" id="root_index">
		<div class="warp">
			<div class="container-fluid">
				<el-row>
					<el-col :span="4">
						<div_label bg_color="bg_purple" icon="el-icon-user-solid" :url="url_user_count" unit="人"
								  title="用户数量"></div_label>
					</el-col>
				</el-row>

				<el-row>
										<el-col v-if="user_group == '管理员' || $check_figure('/unmanned_vehicle_information/table')" :span="8">
						<div class="card chart">
																																												<pieChart v-if="list_unmanned_vehicle_information.length" id="list_unmanned_vehicle_information" :list="list_unmanned_vehicle_information" :title="'无人车信息统计'"></pieChart>
							<div v-if="!list_unmanned_vehicle_information.length">无人车信息没有符合条件的数据</div>
															</div>
					</el-col>
								<el-col v-if="user_group == '管理员' || $check_figure('/product_inventory/table')" :span="8">
						<div class="card chart">
									<newLineChart v-if="line_obj_product_inventory.values.length > 0" id="line_obj_product_inventory" :vm="line_obj_product_inventory" :title="'商品仓库统计'">
							</newLineChart>
							<div v-if="!line_obj_product_inventory.values.length">商品仓库没有符合条件的数据</div>
								</div>
					</el-col>
							<el-col v-if="user_group == '管理员' || $check_figure('/storage_information/table')" :span="8">
						<div class="card chart">
									<gradientStackedAreaChart v-if="line_obj_storage_information.values.length > 0" id="line_obj_storage_information" :vm="line_obj_storage_information" :title="'入库信息统计'">
							</gradientStackedAreaChart>
							<div v-if="!line_obj_storage_information.values.length">入库信息没有符合条件的数据</div>
								</div>
					</el-col>
							<el-col v-if="user_group == '管理员' || $check_figure('/outbound_information/table')" :span="8">
						<div class="card chart">
									<newBarChart v-if="bar_obj_outbound_information.values.length > 0" id="bar_obj_outbound_information" :vm="bar_obj_outbound_information" :title="'出库信息统计'">
							</newBarChart>
							<div v-if="!bar_obj_outbound_information.values.length">出库信息没有符合条件的数据</div>
								</div>
					</el-col>
						</el-row>


			</div>
		</div>
	</div>
</template>
<script>
	import mixin from "@/mixins/page.js";
	import pieChart from "@/components/charts/pie_chart";
	import newBarChart from "@/components/charts/new_bar_chart";
	import newLineChart from "@/components/charts/new_line_chart";
	import gradientStackedAreaChart from "@/components/charts/gradient_stacked_area_chart";
	import div_label from "@/components/div_label.vue";
	export default {
		mixins: [mixin],
		name: "Home",
		components: {
			pieChart,
			newBarChart,
			newLineChart,
			gradientStackedAreaChart,
			div_label
		},
		data() {
			return {
				activeName: "third",
													list_unmanned_vehicle_information: [],
								line_obj_product_inventory:{
					names:[],
					xAxis: [],
					values:[]
				},
						line_obj_storage_information:{
					names:[],
					xAxis: [],
					values:[]
				},
						bar_obj_outbound_information: {
					names:[],
					xAxis: [],
					values:[]
				},
							url_user_count: "~/api/user/count?",
			};
		},
		created() {
								// 执行无人车信息数据获取
			this.get_list_unmanned_vehicle_information();
						// 执行商品仓库数据获取
			this.get_list_product_inventory();
					// 执行入库信息数据获取
			this.get_list_storage_information();
					// 执行出库信息数据获取
			this.get_list_outbound_information();
				},
		mounted() {},
		methods: {
			async get_nickname(list,flag){
				if (flag) {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i],
								null,
								(json) => {
									if (json.result) {
										list[i] = json.result.obj.nickname;
									}
								});
					}
				}else {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i].name,
								null,
								(json) => {
									if (json.result) {
										list[i].name = json.result.obj.nickname;
									}
								});
					}
				}
			},
																																					// 获取无人车信息统计图数据
			get_list_unmanned_vehicle_information() {
				let data = {};
								let flag = false;
												let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
										let sqlwhere = "(";
																		if (user_group=="员工用户"){
						sqlwhere+= "employee_users = " + user_id + " or ";
					}
																																																																						if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						data.sqlwhere = sqlwhere;
					}
									}
								this.$get("~/api/unmanned_vehicle_information/list_group?groupby=unmanned_vehicle_status", data, (json) => {
					if (json.result) {
						var list = json.result.list;
						this.list_unmanned_vehicle_information = list.map((o) => {
							return {
												name: o[1],
												value: o[0]
							};
						});
						if (flag){
							this.get_nickname(this.list_unmanned_vehicle_information,false);
						}
					}
				});
			},
												// 获取商品仓库统计图数据
			async get_list_product_inventory() {
									let group_by_value = "product_name";
				let data = {};
								let flag = false;
												await this.$get(
					"~/api/product_inventory/get_list?groupby="+group_by_value,data,
					(json) => {
						if (json.result) {
							let list = json.result.list;
							let name_list = [];
							for (let i=0;i<list.length;i++){
								name_list.push(list[i].product_name);
							}
							this.line_obj_product_inventory.names = name_list;
							this.get_list_product_inventory_sub("product_name",flag);
						}
				});
																																	},
			async get_list_product_inventory_sub(v1,names_flag) {
																																		let data = {};
								let flag = false;
												await this.$get(
					"~/api/product_inventory/get_list?groupby=note_details",data,
					(json) => {
						if (json.result) {
							let list = json.result.list;
							let xAxis_list = [];
							for (let i=0;i<list.length;i++){
												xAxis_list.push(list[i].note_details);
											}
							this.line_obj_product_inventory.xAxis = xAxis_list;
							this.get_list_product_inventory_sub_sub(v1,"note_details",names_flag,flag);
						}
				});
								},
			async get_list_product_inventory_sub_sub(v1,v2,names_flag,xAxis_flag) {
																													let data_str = "{\""+v1+"\":\"\",\""+v2+"\":\"\"}";
				let data = JSON.parse(data_str);
								for (let i=0;i<this.line_obj_product_inventory.xAxis.length;i++){
					let list = []
					for (let j=0;j<this.line_obj_product_inventory.names.length;j++){
						data[v2] = this.line_obj_product_inventory.xAxis[i];
						data[v1] = this.line_obj_product_inventory.names[j];
						await this.$get(
								"~/api/product_inventory/sum?field=inventory_quantity",
								data,
								(json) => {
									if (json.result) {
										list[j] = json.result;
									}else {
										list[j] = 0;
									}
								});
					}
					this.line_obj_product_inventory.values.push(list)
				}
				if (names_flag){
					this.get_nickname(this.line_obj_product_inventory.names,true);
				}
				if (xAxis_flag){
					this.get_nickname(this.line_obj_product_inventory.xAxis,true);
				}
													},
					// 获取入库信息统计图数据
			async get_list_storage_information() {
									let group_by_value = "product_number";
				let data = {};
								let flag = false;
												await this.$get(
					"~/api/storage_information/get_list?groupby="+group_by_value,data,
					(json) => {
						if (json.result) {
							let list = json.result.list;
							let name_list = [];
							for (let i=0;i<list.length;i++){
								name_list.push(list[i].product_number);
							}
							this.line_obj_storage_information.names = name_list;
							this.get_list_storage_information_sub("product_number",flag);
						}
				});
																																	},
			async get_list_storage_information_sub(v1,names_flag) {
																																		let data = {};
								let flag = false;
												await this.$get(
					"~/api/storage_information/get_list?groupby=storage_remarks",data,
					(json) => {
						if (json.result) {
							let list = json.result.list;
							let xAxis_list = [];
							for (let i=0;i<list.length;i++){
												xAxis_list.push(list[i].storage_remarks);
											}
							this.line_obj_storage_information.xAxis = xAxis_list;
							this.get_list_storage_information_sub_sub(v1,"storage_remarks",names_flag,flag);
						}
				});
								},
			async get_list_storage_information_sub_sub(v1,v2,names_flag,xAxis_flag) {
																								let data_str = "{\""+v1+"\":\"\",\""+v2+"\":\"\"}";
				let data = JSON.parse(data_str);
								for (let i=0;i<this.line_obj_storage_information.xAxis.length;i++){
					let list = []
					for (let j=0;j<this.line_obj_storage_information.names.length;j++){
						data[v2] = this.line_obj_storage_information.xAxis[i];
						data[v1] = this.line_obj_storage_information.names[j];
						await this.$get(
								"~/api/storage_information/sum?field=inventory_quantity",
								data,
								(json) => {
									if (json.result) {
										list[j] = json.result;
									}else {
										list[j] = 0;
									}
								});
					}
					this.line_obj_storage_information.values.push(list)
				}
				if (names_flag){
					this.get_nickname(this.line_obj_storage_information.names,true);
				}
				if (xAxis_flag){
					this.get_nickname(this.line_obj_storage_information.xAxis,true);
				}
																		},
					// 获取出库信息统计图
			async get_list_outbound_information() {
				let name_list = [];
				let query_str = "";
																	let group_by_value = "product_name";
								let flag = false;
												let date_flag = "其他"
																														name_list.push("出库数量");
				query_str = query_str+"outbound_quantity"+","
																									this.bar_obj_outbound_information.names = name_list
				query_str = query_str.substr(0,query_str.length-1);
				let data = {};
						await this.$get(
						"~/api/outbound_information/bar_group?field="+query_str+"&groupby="+group_by_value,
						data,
						(json) => {
							if (json.result) {
								let xAxis = [];
								let values = [];
								json.result.list.map((o) => {
									if (date_flag === "日期") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd"));
									}else if (date_flag === "时间") {
										xAxis.push(this.$toTime(o[0] ,"hh:mm:ss"));
									}else if (date_flag === "日长") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd hh:mm:ss"));
									}else {
										xAxis.push(o[0]);
									}
									values.push(o.splice(1))
								});
								this.bar_obj_outbound_information.xAxis = xAxis;
								this.bar_obj_outbound_information.values = values;
							}
							if (flag){
								this.get_nickname(this.bar_obj_outbound_information.xAxis,true);
							}
						});
			},
			
		},
		computed:{
			recognitionHeight(){
				return "830px"
			},
			recognitionUrl(){
				return "https://www.faceplusplus.com.cn/${model.filter.recognitionType}/"
			}
		}
	};
</script>

<style scoped="scoped">
	.chart {
		display: block;
		width: 100%;
		height: 400px;
		padding: 1rem;
		position: relative;
	}

	.el-col {
		padding: 0.5rem;
	}

	.card {
		overflow: hidden;
	}

	.iframe_box ,.iframe_box_change{
		width: 100%;
		height: 1180px;
		position: relative;
		margin-top: 25px;
	}
	.iframe_box_change{
		height: 580px;
		padding-top: 50px;
	}
	.iframe_box	.iframe_box_content, .iframe_box_change .iframe_box_content{
		width: 100%;
		height: 100%;
	}
	.iframe_box_top{
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100px;
		font-size: 25px;
		line-height: 100px;
		background: #fff;
		z-index: 99999999;
		padding-left: 50px;
	}
	#iframe_box_face div::before {
		content: '';
		width: 100px;
		position: absolute;
		top: 154px;
		right: 129px;
		z-index: 999;
		height: 20px;
		background-color: #FFFFFF;
	}

	#iframe_box_face>h1 {
		margin-top: 100px;
		margin-bottom: 20px;
	}
</style>
