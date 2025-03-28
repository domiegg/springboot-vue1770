<template>
	<div>
		<el-form-item v-if="$check_register_field('add','employee_job_number','/ordinary_users/view')" label="员工工号" prop="employee_job_number">
		<el-input id="employee_job_number" v-model="form['employee_job_number']" placeholder="请输入员工工号"></el-input>
		</el-form-item>
		<el-form-item v-if="$check_register_field('add','employee_name','/ordinary_users/view')" label="员工姓名" prop="employee_name">
		<el-input id="employee_name" v-model="form['employee_name']" placeholder="请输入员工姓名"></el-input>
		</el-form-item>
		<el-form-item v-if="$check_register_field('add','gender','/ordinary_users/view')" label="性别" prop="gender">
		<el-select id="gender" v-model="form['gender']">
			<el-option v-for="o in list_gender" :key="o" :label="o" :value="o">
			</el-option>
		</el-select>
		</el-form-item>
		<el-form-item v-if="$check_register_field('add','age','/ordinary_users/view')" label="年龄" prop="age">
		<el-input id="age" v-model="form['age']" placeholder="请输入年龄"></el-input>
		</el-form-item>

	</div>
</template>

<script>
	import mixin from "@/mixins/component.js";

	export default {
		mixins: [mixin],
		props:{

			form:{
				type: Object,
				default: function(){
					return {
						"employee_job_number":'',
						"employee_name":'',
						"gender":'',
						"age":'',
					}
				}
			},

		},
		data() {
			return {
				field: "ordinary_users_id",
				url_add: "~/api/ordinary_users/add?",
				url_set: "~/api/ordinary_users/set?",
				url_upload: "~/api/ordinary_users/upload?",
				list_gender: ['男','女'],

				rules: {
					"employee_job_number": [ ],
					"employee_name": [ ],
					"gender": [ ],
					"age": [ ],
				}

			}
		},
		methods: {

		},
		created() {
		}
	}
</script>
