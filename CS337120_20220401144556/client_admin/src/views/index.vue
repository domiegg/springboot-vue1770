<template>
	<div class="page_root" id="root_index">
		<div class="warp">
			<div class="container-fluid">
				<el-row>
					<el-col :span="4">
						<mm_label bg_color="bg_purple" icon="el-icon-user-solid" :url="url_user_count" unit="人"
								  title="用户数量"></mm_label>
					</el-col>
				</el-row>

				<el-row>
					<el-col v-if="user_group == '管理员' || $check_figure('/ordinary_users/table')" :span="8">
						<div class="card chart">
							<pieChart v-if="list_ordinary_users.length" id="list_ordinary_users" :list="list_ordinary_users" :title="'普通用户统计'"></pieChart>
							<div v-if="!list_ordinary_users.length">普通用户没有符合条件的数据</div>
						</div>
					</el-col>
					<el-col v-if="user_group == '管理员' || $check_figure('/employee_profile_information/table')" :span="8">
						<div class="card chart">
							<pieChart v-if="list_employee_profile_information.length" id="list_employee_profile_information" :list="list_employee_profile_information" :title="'员工档案信息统计'"></pieChart>
							<div v-if="!list_employee_profile_information.length">员工档案信息没有符合条件的数据</div>
						</div>
					</el-col>
					<el-col v-if="user_group == '管理员' || $check_figure('/late_personnel_registration/table')" :span="8">
						<div class="card chart">
							<newBarChart v-if="bar_obj_late_personnel_registration.values.length > 0" id="bar_obj_late_personnel_registration" :vm="bar_obj_late_personnel_registration" :title="'迟到人员登记统计'">
							</newBarChart>
							<div v-if="!bar_obj_late_personnel_registration.values.length">迟到人员登记没有符合条件的数据</div>
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
	import barChart from "@/components/charts/bar_chart";
	import newBarChart from "@/components/charts/new_bar_chart";
	import lineChart from "@/components/charts/line_chart";
	import newLineChart from "@/components/charts/new_line_chart";
	import mm_label from "@/components/mm_label.vue";
	export default {
		mixins: [mixin],
		name: "Home",
		components: {
			pieChart,
			barChart,
			newBarChart,
			lineChart,
			newLineChart,
			mm_label
		},
		data() {
			return {
				isAdmin: false,
				recognitionType: "",
				activeName: "third",
				list_ordinary_users: [],
				list_employee_profile_information: [],
				bar_obj_late_personnel_registration: {
					names:[],
					xAxis: [],
					values:[]
				},
				url_user_count: "~/api/user/count?",
			};
		},
		created() {
			this.getUserInfo();
			// 执行普通用户数据获取
			this.get_list_ordinary_users();
			// 执行员工档案信息数据获取
			this.get_list_employee_profile_information();
			// 执行迟到人员登记数据获取
			this.get_list_late_personnel_registration();
			
			
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
			// 获取普通用户统计图数据
			get_list_ordinary_users() {
				let data = {};
				let flag = false;
				this.$get("~/api/ordinary_users/list_group?groupby=gender", data, (json) => {
					if (json.result) {
						var list = json.result.list;
						this.list_ordinary_users = list.map((o) => {
							return {
								name: o[1],
								value: o[0]
							};
						});
						if (flag){
							this.get_nickname(this.list_ordinary_users,false);
						}
					}
				});
			},
			// 获取员工档案信息统计图数据
			get_list_employee_profile_information() {
				let data = {};
				let flag = false;
				this.$get("~/api/employee_profile_information/list_group?groupby=employee_status", data, (json) => {
					if (json.result) {
						var list = json.result.list;
						this.list_employee_profile_information = list.map((o) => {
							return {
								name: o[1],
								value: o[0]
							};
						});
						if (flag){
							this.get_nickname(this.list_employee_profile_information,false);
						}
					}
				});
			},
			// 获取迟到人员登记统计柱状图
			async get_list_late_personnel_registration() {
				let name_list = [];
				let query_str = "";
				let group_by_value = "employee_name";
				let flag = false;
				let date_flag = "其他"
				name_list.push("迟到次数");
				query_str = query_str+"late_times"+","
				this.bar_obj_late_personnel_registration.names = name_list
				query_str = query_str.substr(0,query_str.length-1);
				let data = {};
				let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
					if (user_group=="普通用户"){
						data.employee_job_number = user_id;
					}
				}
				await this.$get(
						"~/api/late_personnel_registration/bar_group?field="+query_str+"&groupby="+group_by_value,
						data,
						(json) => {
							if (json.result) {
								let xAxis = [];
								let values = [];
								json.result.list.map((o) => {
									if (date_flag === "日期") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd"));
									}else if (date_flag === "时间") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd hh:mm:ss"));
									}else {
										xAxis.push(o[0]);
									}
									values.push(o.splice(1))
								});
								this.bar_obj_late_personnel_registration.xAxis = xAxis;
								this.bar_obj_late_personnel_registration.values = values;
							}
							if (flag){
								this.get_nickname(this.bar_obj_late_personnel_registration.xAxis,true);
							}
						});
			},
				getUserInfo(){
					let userGroup = window.localStorage.getItem('user_group');
						if(userGroup){
						let _userGroup =JSON.parse(userGroup);
						let _info = JSON.parse(_userGroup.value);
						this.isAdmin = _info["user_group"] == "管理员" ? true : false;
					}
			}

		},
		computed:{
			recognitionHeight(){
				if(this.recognitionType === "face"){
					return "1070px"
				}else{
					return "1180px"
				}
			},
			recognitionUrl(){
					if(this.recognitionType === "face"){
					return "https://www.sk-ai.com/Experience/face-compare"
				}else{
					return "https://www.sk-ai.com/Experience/recognition?type="
				}
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
</style>
