<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','employee_job_number') || $check_field('add','employee_job_number') || $check_field('set','employee_job_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="员工工号" prop="employee_job_number">
						<el-select v-if="user_group === '管理员' || (form['late_personnel_registration_id'] && $check_field('set','employee_job_number')) || (!form['late_personnel_registration_id'] && $check_field('add','employee_job_number'))" id="employee_job_number" v-model="form['employee_job_number']" :disabled="disabledObj['employee_job_number_isDisabled']">
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
							  v-if="user_group === '管理员' || (form['late_personnel_registration_id'] && $check_field('set','employee_name')) || (!form['late_personnel_registration_id'] && $check_field('add','employee_name'))" :disabled="disabledObj['employee_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','employee_name')">{{form['employee_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','punch_in_status') || $check_field('add','punch_in_status') || $check_field('set','punch_in_status')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="打卡状态" prop="punch_in_status">
					<el-input id="punch_in_status" v-model="form['punch_in_status']" placeholder="请输入打卡状态"
							  v-if="user_group === '管理员' || (form['late_personnel_registration_id'] && $check_field('set','punch_in_status')) || (!form['late_personnel_registration_id'] && $check_field('add','punch_in_status'))" :disabled="disabledObj['punch_in_status_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','punch_in_status')">{{form['punch_in_status']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','late_times') || $check_field('add','late_times') || $check_field('set','late_times')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="迟到次数" prop="late_times">
					<el-select id="late_times" v-model="form['late_times']"
						v-if="user_group === '管理员' || (form['late_personnel_registration_id'] && $check_field('set','late_times')) || (!form['late_personnel_registration_id'] && $check_field('add','late_times'))">
						<el-option v-for="o in list_late_times" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','late_times')">{{form['late_times']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','remarks') || $check_field('add','remarks') || $check_field('set','remarks')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="备注" prop="remarks">
					<el-input type="textarea" id="remarks" v-model="form['remarks']" placeholder="请输入备注"
						v-if="user_group === '管理员' || (form['late_personnel_registration_id'] && $check_field('set','remarks')) || (!form['late_personnel_registration_id'] && $check_field('add','remarks'))" :disabled="disabledObj['remarks_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','remarks')">{{form['remarks']}}</div>
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
				field: "late_personnel_registration_id",
				url_add: "~/api/late_personnel_registration/add?",
				url_set: "~/api/late_personnel_registration/set?",
				url_get_obj: "~/api/late_personnel_registration/get_obj?",
				url_upload: "~/api/late_personnel_registration/upload?",

				query: {
					"late_personnel_registration_id": 0,
				},

				form: {
					"employee_job_number": 0, // 员工工号
					"employee_name":'', // 员工姓名
					"punch_in_status":'', // 打卡状态
					"late_times":'', // 迟到次数
					"remarks":'', // 备注
					"late_personnel_registration_id": 0, // ID

				},
				disabledObj:{
					"employee_job_number_isDisabled": false,
					"employee_name_isDisabled": false,
					"punch_in_status_isDisabled": false,
					"late_times_isDisabled": false,
					"remarks_isDisabled": false,
				},
				// 用户列表
				list_user_employee_job_number: [],
				//迟到次数选项列表
				list_late_times: ['1'],

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

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/late_personnel_registration/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/late_personnel_registration/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/late_personnel_registration/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/late_personnel_registration/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/late_personnel_registration/view','get');
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
