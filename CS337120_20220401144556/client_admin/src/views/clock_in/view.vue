<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','punch_in_title') || $check_field('add','punch_in_title') || $check_field('set','punch_in_title')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="打卡标题" prop="punch_in_title">
					<el-input id="punch_in_title" v-model="form['punch_in_title']" placeholder="请输入打卡标题"
							  v-if="user_group === '管理员' || (form['clock_in_id'] && $check_field('set','punch_in_title')) || (!form['clock_in_id'] && $check_field('add','punch_in_title'))" :disabled="disabledObj['punch_in_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','punch_in_title')">{{form['punch_in_title']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','clock_in_time') || $check_field('add','clock_in_time') || $check_field('set','clock_in_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="上班打卡时间" prop="clock_in_time">
					<el-date-picker :disabled="disabledObj['clock_in_time_isDisabled']" v-if="user_group === '管理员' || (form['clock_in_id'] && $check_field('set','clock_in_time')) || (!form['clock_in_id'] && $check_field('add','clock_in_time'))" id="clock_in_time"
						v-model="form['clock_in_time']" type="datetime" placeholder="选择日期时间">
					</el-date-picker>
					<div v-else-if="$check_field('get','clock_in_time')">{{form['clock_in_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','employee_job_number') || $check_field('add','employee_job_number') || $check_field('set','employee_job_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="员工工号" prop="employee_job_number">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_employee_job_number(form['employee_job_number']) }}
							<!--<el-input id="business_name" v-model="form['employee_job_number']" placeholder="请输入员工工号"-->
							<!--v-if="user_group === '管理员' || (form['clock_in_id'] && $check_field('set','employee_job_number')) || (!form['clock_in_id'] && $check_field('add','employee_job_number'))" :disabled="disabledObj['employee_job_number_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','employee_job_number')">{{form['employee_job_number']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['clock_in_id'] && $check_field('set','employee_job_number')) || (!form['clock_in_id'] && $check_field('add','employee_job_number'))" id="employee_job_number" v-model="form['employee_job_number']" :disabled="disabledObj['employee_job_number_isDisabled']">
								<el-option v-for="o in list_user_employee_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','employee_job_number')" id="employee_job_number" v-model="form['employee_job_number']" :disabled="true">
								<el-option v-for="o in list_user_employee_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="employee_job_number" v-model="form['employee_job_number']" :disabled="disabledObj['employee_job_number_isDisabled']">
							<el-option v-for="o in list_user_employee_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','employee_name') || $check_field('add','employee_name') || $check_field('set','employee_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="员工姓名" prop="employee_name">
					<el-input id="employee_name" v-model="form['employee_name']" placeholder="请输入员工姓名"
							  v-if="user_group === '管理员' || (form['clock_in_id'] && $check_field('set','employee_name')) || (!form['clock_in_id'] && $check_field('add','employee_name'))" :disabled="disabledObj['employee_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','employee_name')">{{form['employee_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','punch_in_status') || $check_field('add','punch_in_status') || $check_field('set','punch_in_status')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="打卡状态" prop="punch_in_status">
					<el-select id="punch_in_status" v-model="form['punch_in_status']"
						v-if="user_group === '管理员' || (form['clock_in_id'] && $check_field('set','punch_in_status')) || (!form['clock_in_id'] && $check_field('add','punch_in_status'))">
						<el-option v-for="o in list_punch_in_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','punch_in_status')">{{form['punch_in_status']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
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
				field: "clock_in_id",
				url_add: "~/api/clock_in/add?",
				url_set: "~/api/clock_in/set?",
				url_get_obj: "~/api/clock_in/get_obj?",
				url_upload: "~/api/clock_in/upload?",

				query: {
					"clock_in_id": 0,
				},

				form: {
					"punch_in_title":'', // 打卡标题
					"clock_in_time":'', // 上班打卡时间
					"employee_job_number": 0, // 员工工号
					"employee_name":'', // 员工姓名
					"punch_in_status":'', // 打卡状态
					"clock_in_id": 0, // ID

				},
				disabledObj:{
					"punch_in_title_isDisabled": false,
					"clock_in_time_isDisabled": false,
					"employee_job_number_isDisabled": false,
					"employee_name_isDisabled": false,
					"punch_in_status_isDisabled": false,
				},
				// 用户列表
				list_user_employee_job_number: [],
				// 用户组
				group_user_employee_job_number: "",
				//打卡状态选项列表
				list_punch_in_status: ['正常','迟到'],

			}
		},
		methods: {
			/**
			 * 获取普通用户用户列表
			 */
			async get_list_user_employee_job_number() {
                // if(this.user_group !== "管理员" && this.form["employee_job_number"] === 0) {
                //     this.form["employee_job_number"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=普通用户");
                if(json.result && json.result.list){
                    this.list_user_employee_job_number = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取普通用户用户组
			 */
			async get_group_user_employee_job_number() {
				this.form["employee_job_number"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					this.group_user_employee_job_number = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_employee_job_number(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_employee_job_number.source_table+"/get_obj?"
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
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="employee_job_number") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
			},
			get_user_employee_job_number(id){
				var obj = this.list_user_employee_job_number.getObj({"user_id":id});
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
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "员工工号":
							if(param["employee_job_number"] > 0){
								param["employee_job_number"] = this.user.user_id;
							}
							break;
					}
				}
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
				this.form["clock_in_time"] = this.$toTime(parseInt(this.form["clock_in_time"]),"yyyy-MM-dd hh:mm:ss")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["clock_in_time"]) > 9999){
					this.form["clock_in_time"] = this.$toTime(parseInt(this.form["clock_in_time"]),"yyyy-MM-dd hh:mm:ss")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/clock_in/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/clock_in/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/clock_in/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/clock_in/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/clock_in/view','get');
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
			this.get_list_user_employee_job_number();
			this.get_group_user_employee_job_number();
		}
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
