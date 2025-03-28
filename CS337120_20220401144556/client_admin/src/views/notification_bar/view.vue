<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','notice_title') || $check_field('add','notice_title') || $check_field('set','notice_title')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="通知标题" prop="notice_title">
					<el-input id="notice_title" v-model="form['notice_title']" placeholder="请输入通知标题"
							  v-if="user_group === '管理员' || (form['notification_bar_id'] && $check_field('set','notice_title')) || (!form['notification_bar_id'] && $check_field('add','notice_title'))" :disabled="disabledObj['notice_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','notice_title')">{{form['notice_title']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','notification_time') || $check_field('add','notification_time') || $check_field('set','notification_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="通知时间" prop="notification_time">
					<el-date-picker :disabled="disabledObj['notification_time_isDisabled']" v-if="user_group === '管理员' || (form['notification_bar_id'] && $check_field('set','notification_time')) || (!form['notification_bar_id'] && $check_field('add','notification_time'))" id="notification_time"
						v-model="form['notification_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','notification_time')">{{form['notification_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','employee_job_number') || $check_field('add','employee_job_number') || $check_field('set','employee_job_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="员工工号" prop="employee_job_number">
						<el-select v-if="user_group === '管理员' || (form['notification_bar_id'] && $check_field('set','employee_job_number')) || (!form['notification_bar_id'] && $check_field('add','employee_job_number'))" id="employee_job_number" v-model="form['employee_job_number']" :disabled="disabledObj['employee_job_number_isDisabled']">
							<el-option v-for="o in list_user_employee_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','employee_job_number')" id="employee_job_number" v-model="form['employee_job_number']" :disabled="true">
							<el-option v-for="o in list_user_employee_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','employee_name') || $check_field('add','employee_name') || $check_field('set','employee_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="员工姓名" prop="employee_name">
					<el-input id="employee_name" v-model="form['employee_name']" placeholder="请输入员工姓名"
							  v-if="user_group === '管理员' || (form['notification_bar_id'] && $check_field('set','employee_name')) || (!form['notification_bar_id'] && $check_field('add','employee_name'))" :disabled="disabledObj['employee_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','employee_name')">{{form['employee_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','notice_content') || $check_field('add','notice_content') || $check_field('set','notice_content')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="通知内容" prop="notice_content">
					<el-input type="textarea" id="notice_content" v-model="form['notice_content']" placeholder="请输入通知内容"
						v-if="user_group === '管理员' || (form['notification_bar_id'] && $check_field('set','notice_content')) || (!form['notification_bar_id'] && $check_field('add','notice_content'))" :disabled="disabledObj['notice_content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','notice_content')">{{form['notice_content']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','employee_reply') || $check_field('add','employee_reply') || $check_field('set','employee_reply')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="员工回复" prop="employee_reply">
					<el-input type="textarea" id="employee_reply" v-model="form['employee_reply']" placeholder="请输入员工回复"
						v-if="user_group === '管理员' || (form['notification_bar_id'] && $check_field('set','employee_reply')) || (!form['notification_bar_id'] && $check_field('add','employee_reply'))" :disabled="disabledObj['employee_reply_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','employee_reply')">{{form['employee_reply']}}</div>
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
				field: "notification_bar_id",
				url_add: "~/api/notification_bar/add?",
				url_set: "~/api/notification_bar/set?",
				url_get_obj: "~/api/notification_bar/get_obj?",
				url_upload: "~/api/notification_bar/upload?",

				query: {
					"notification_bar_id": 0,
				},

				form: {
					"notice_title":'', // 通知标题
					"notification_time":'', // 通知时间
					"employee_job_number": 0, // 员工工号
					"employee_name":'', // 员工姓名
					"notice_content":'', // 通知内容
					"employee_reply":'', // 员工回复
					"notification_bar_id": 0, // ID

				},
				disabledObj:{
					"notice_title_isDisabled": false,
					"notification_time_isDisabled": false,
					"employee_job_number_isDisabled": false,
					"employee_name_isDisabled": false,
					"notice_content_isDisabled": false,
					"employee_reply_isDisabled": false,
				},
				// 用户列表
				list_user_employee_job_number: [],

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
				// 获取缓存数据附加
				form = $.db.get("form");
				$.push(this.form ,form);
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
        if (this.form["notification_time"].indexOf("-")===-1){
          this.form["notification_time"] = this.$toTime(parseInt(this.form["notification_time"]),"yyyy-MM-dd")
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
				if(parseInt(this.form["notification_time"]) > 9999){
					this.form["notification_time"] = this.$toTime(parseInt(this.form["notification_time"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/notification_bar/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/notification_bar/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/notification_bar/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/notification_bar/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/notification_bar/view','get');
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
