<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','month') || $check_field('add','month') || $check_field('set','month')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="月份" prop="month">
					<el-input id="month" v-model="form['month']" placeholder="请输入月份"
							  v-if="user_group === '管理员' || (form['pay_slip_id'] && $check_field('set','month')) || (!form['pay_slip_id'] && $check_field('add','month'))" :disabled="disabledObj['month_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','month')">{{form['month']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','base_pay') || $check_field('add','base_pay') || $check_field('set','base_pay')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="基本工资" prop="base_pay">
					<el-input-number id="base_pay" v-model.number="form['base_pay']"
						v-if="user_group === '管理员' || (form['pay_slip_id'] && $check_field('set','base_pay')) || (!form['pay_slip_id'] && $check_field('add','base_pay'))"></el-input-number>
					<div v-else-if="$check_field('get','base_pay')">{{form['base_pay']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','achievements') || $check_field('add','achievements') || $check_field('set','achievements')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="绩效" prop="achievements">
					<el-input-number id="achievements" v-model.number="form['achievements']"
						v-if="user_group === '管理员' || (form['pay_slip_id'] && $check_field('set','achievements')) || (!form['pay_slip_id'] && $check_field('add','achievements'))"></el-input-number>
					<div v-else-if="$check_field('get','achievements')">{{form['achievements']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','allowance') || $check_field('add','allowance') || $check_field('set','allowance')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="津贴" prop="allowance">
					<el-input-number id="allowance" v-model.number="form['allowance']"
						v-if="user_group === '管理员' || (form['pay_slip_id'] && $check_field('set','allowance')) || (!form['pay_slip_id'] && $check_field('add','allowance'))"></el-input-number>
					<div v-else-if="$check_field('get','allowance')">{{form['allowance']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','overtime_pay') || $check_field('add','overtime_pay') || $check_field('set','overtime_pay')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="加班费" prop="overtime_pay">
					<el-input-number id="overtime_pay" v-model.number="form['overtime_pay']"
						v-if="user_group === '管理员' || (form['pay_slip_id'] && $check_field('set','overtime_pay')) || (!form['pay_slip_id'] && $check_field('add','overtime_pay'))"></el-input-number>
					<div v-else-if="$check_field('get','overtime_pay')">{{form['overtime_pay']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','five_insurances_and_one_fund') || $check_field('add','five_insurances_and_one_fund') || $check_field('set','five_insurances_and_one_fund')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="五险一金" prop="five_insurances_and_one_fund">
					<el-input-number id="five_insurances_and_one_fund" v-model.number="form['five_insurances_and_one_fund']"
						v-if="user_group === '管理员' || (form['pay_slip_id'] && $check_field('set','five_insurances_and_one_fund')) || (!form['pay_slip_id'] && $check_field('add','five_insurances_and_one_fund'))"></el-input-number>
					<div v-else-if="$check_field('get','five_insurances_and_one_fund')">{{form['five_insurances_and_one_fund']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','absence_deduction') || $check_field('add','absence_deduction') || $check_field('set','absence_deduction')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="缺勤扣除" prop="absence_deduction">
					<el-input-number id="absence_deduction" v-model.number="form['absence_deduction']"
						v-if="user_group === '管理员' || (form['pay_slip_id'] && $check_field('set','absence_deduction')) || (!form['pay_slip_id'] && $check_field('add','absence_deduction'))"></el-input-number>
					<div v-else-if="$check_field('get','absence_deduction')">{{form['absence_deduction']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','net_salary') || $check_field('add','net_salary') || $check_field('set','net_salary')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="实发工资" prop="net_salary">
					<el-input id="net_salary" v-model="form['net_salary']" placeholder="请输入实发工资"
							  v-if="user_group === '管理员' || (form['pay_slip_id'] && $check_field('set','net_salary')) || (!form['pay_slip_id'] && $check_field('add','net_salary'))"  @focus="set_net_salary()" :disabled="disabledObj['net_salary_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','net_salary')">{{form['net_salary']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','employee_job_number') || $check_field('add','employee_job_number') || $check_field('set','employee_job_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="员工工号" prop="employee_job_number">
						<el-select v-if="user_group === '管理员' || (form['pay_slip_id'] && $check_field('set','employee_job_number')) || (!form['pay_slip_id'] && $check_field('add','employee_job_number'))" id="employee_job_number" v-model="form['employee_job_number']" :disabled="disabledObj['employee_job_number_isDisabled']">
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
							  v-if="user_group === '管理员' || (form['pay_slip_id'] && $check_field('set','employee_name')) || (!form['pay_slip_id'] && $check_field('add','employee_name'))" :disabled="disabledObj['employee_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','employee_name')">{{form['employee_name']}}</div>
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
				field: "pay_slip_id",
				url_add: "~/api/pay_slip/add?",
				url_set: "~/api/pay_slip/set?",
				url_get_obj: "~/api/pay_slip/get_obj?",
				url_upload: "~/api/pay_slip/upload?",

				query: {
					"pay_slip_id": 0,
				},

				form: {
					"month":'', // 月份
					"base_pay":0, // 基本工资
					"achievements":0, // 绩效
					"allowance":0, // 津贴
					"overtime_pay":0, // 加班费
					"five_insurances_and_one_fund":0, // 五险一金
					"absence_deduction":0, // 缺勤扣除
					"net_salary":'', // 实发工资
					"employee_job_number": 0, // 员工工号
					"employee_name":'', // 员工姓名
					"pay_slip_id": 0, // ID

				},
				disabledObj:{
					"month_isDisabled": false,
					"net_salary_isDisabled": false,
					"employee_job_number_isDisabled": false,
					"employee_name_isDisabled": false,
				},
				// 用户列表
				list_user_employee_job_number: [],

			}
		},
		methods: {
			set_net_salary(){
				this.form.net_salary =this.form.base_pay + this.form.achievements + this.form.allowance + this.form.overtime_pay - this.form.five_insurances_and_one_fund - this.form.absence_deduction
			},
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
					bl = this.$check_action('/pay_slip/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/pay_slip/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/pay_slip/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/pay_slip/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/pay_slip/view','get');
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
