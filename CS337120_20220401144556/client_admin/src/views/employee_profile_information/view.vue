<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','employee_number') || $check_field('add','employee_number') || $check_field('set','employee_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="员工编号" prop="employee_number">
					<el-input id="employee_number" v-model="form['employee_number']" placeholder="请输入员工编号"
							  v-if="user_group === '管理员' || (form['employee_profile_information_id'] && $check_field('set','employee_number')) || (!form['employee_profile_information_id'] && $check_field('add','employee_number'))" :disabled="true"></el-input>
					<div v-else-if="$check_field('get','employee_number')">{{form['employee_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','employee_name') || $check_field('add','employee_name') || $check_field('set','employee_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="员工姓名" prop="employee_name">
					<el-input id="employee_name" v-model="form['employee_name']" placeholder="请输入员工姓名"
							  v-if="user_group === '管理员' || (form['employee_profile_information_id'] && $check_field('set','employee_name')) || (!form['employee_profile_information_id'] && $check_field('add','employee_name'))" :disabled="disabledObj['employee_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','employee_name')">{{form['employee_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','gender') || $check_field('add','gender') || $check_field('set','gender')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="性别" prop="gender">
					<el-select id="gender" v-model="form['gender']"
						v-if="user_group === '管理员' || (form['employee_profile_information_id'] && $check_field('set','gender')) || (!form['employee_profile_information_id'] && $check_field('add','gender'))">
						<el-option v-for="o in list_gender" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','gender')">{{form['gender']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','age') || $check_field('add','age') || $check_field('set','age')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="年龄" prop="age">
					<el-input id="age" v-model="form['age']" placeholder="请输入年龄"
							  v-if="user_group === '管理员' || (form['employee_profile_information_id'] && $check_field('set','age')) || (!form['employee_profile_information_id'] && $check_field('add','age'))" :disabled="disabledObj['age_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','age')">{{form['age']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','position') || $check_field('add','position') || $check_field('set','position')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="职位" prop="position">
					<el-input id="position" v-model="form['position']" placeholder="请输入职位"
							  v-if="user_group === '管理员' || (form['employee_profile_information_id'] && $check_field('set','position')) || (!form['employee_profile_information_id'] && $check_field('add','position'))" :disabled="disabledObj['position_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','position')">{{form['position']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','employee_status') || $check_field('add','employee_status') || $check_field('set','employee_status')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="员工状态" prop="employee_status">
					<el-select id="employee_status" v-model="form['employee_status']"
						v-if="user_group === '管理员' || (form['employee_profile_information_id'] && $check_field('set','employee_status')) || (!form['employee_profile_information_id'] && $check_field('add','employee_status'))">
						<el-option v-for="o in list_employee_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','employee_status')">{{form['employee_status']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','entry_date') || $check_field('add','entry_date') || $check_field('set','entry_date')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="入职日期" prop="entry_date">
					<el-date-picker :disabled="disabledObj['entry_date_isDisabled']" v-if="user_group === '管理员' || (form['employee_profile_information_id'] && $check_field('set','entry_date')) || (!form['employee_profile_information_id'] && $check_field('add','entry_date'))" id="entry_date"
						v-model="form['entry_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','entry_date')">{{form['entry_date']}}</div>
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
				field: "employee_profile_information_id",
				url_add: "~/api/employee_profile_information/add?",
				url_set: "~/api/employee_profile_information/set?",
				url_get_obj: "~/api/employee_profile_information/get_obj?",
				url_upload: "~/api/employee_profile_information/upload?",

				query: {
					"employee_profile_information_id": 0,
				},

				form: {
					"employee_number":this.$get_stamp(), // 员工编号
					"employee_name":'', // 员工姓名
					"gender":'', // 性别
					"age":'', // 年龄
					"position":'', // 职位
					"employee_status":'', // 员工状态
					"entry_date":'', // 入职日期
					"employee_profile_information_id": 0, // ID

				},
				disabledObj:{
					"employee_number_isDisabled": false,
					"employee_name_isDisabled": false,
					"gender_isDisabled": false,
					"age_isDisabled": false,
					"position_isDisabled": false,
					"employee_status_isDisabled": false,
					"entry_date_isDisabled": false,
				},
				//性别选项列表
				list_gender: ['男','女'],
				//员工状态选项列表
				list_employee_status: ['在职','离职','退休'],

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
        if (this.form["entry_date"].indexOf("-")===-1){
          this.form["entry_date"] = this.$toTime(parseInt(this.form["entry_date"]),"yyyy-MM-dd")
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
				if(parseInt(this.form["entry_date"]) > 9999){
					this.form["entry_date"] = this.$toTime(parseInt(this.form["entry_date"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/employee_profile_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/employee_profile_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/employee_profile_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/employee_profile_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/employee_profile_information/view','get');
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
