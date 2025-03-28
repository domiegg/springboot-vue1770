<template>
	<el-main class="bg">
		<el-form label-position="right" :model="query" class="form p_4" label-width="120">
			<el-row>

				<el-col :xs="24" :sm="24" :lg="8">
					<el-form-item label="名称">
						<el-input v-model="query.name"></el-input>
					</el-form-item>
				</el-col>

				<el-col :xs="24" :sm="24" :lg="24">
					<el-col :xs="24" :sm="12" :lg="12">
						<el-form-item>
							<el-button type="primary" @click="search()">查询</el-button>
							<el-button @click="reset()">重置</el-button>
						</el-form-item>
					</el-col>
					<el-col :xs="24" :sm="12" :lg="12">
						<el-form-item>
							<el-button v-if="user_group == '管理员' || $check_action('/user_group/table','del')" class="float-right" type="danger" @click="delInfo()">删除</el-button>
							<router-link v-if="user_group == '管理员' || $check_action('/user_group/view')" class="el-button float-right el-button--default el-button--primary"
								to="./view?">添加
							</router-link>
						</el-form-item>
					</el-col>
				</el-col>

			</el-row>
		</el-form>

		<el-table border :data="list" @selection-change="selectionChange" @sort-change="$sortChange" style="width: 100%" stripe>

			<el-table-column fixed type="selection" tooltip-effect="dark" width="55">
			</el-table-column>

			<el-table-column fixed prop="name" label="名称" sortable width="200">
			</el-table-column>

			<el-table-column prop="display" label="显示顺序" sortable min-width="120">
			</el-table-column>

			<el-table-column prop="description" label="描述" min-width="400">
			</el-table-column>

			<el-table-column sortable prop="create_time" label="创建时间" min-width="200">
			    <template slot-scope="scope">
			        {{ $toTime(scope.row["create_time"],"yyyy-MM-dd hh:mm:ss") }}
			    </template>
			</el-table-column>

			<el-table-column sortable prop="update_time" label="更新时间" min-width="200">
			    <template slot-scope="scope">
			        {{ $toTime(scope.row["update_time"],"yyyy-MM-dd hh:mm:ss") }}
			    </template>
			</el-table-column>

			<!-- 操作 -->
			<el-table-column fixed="right" label="操作" width="80">
				<template slot-scope="scope">
					<router-link class="el-button el-button--small is-plain el-button--primary"
						:to="'./view?' + field + '=' + scope.row[field]" size="small">
            详情
					</router-link>
				</template>
			</el-table-column>
			<!-- /操作 -->

		</el-table>

		<!-- 分页器 -->
		<div class="mt text_center">
			<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="query.page"
			 :page-sizes="[7, 10, 30, 100]" :page-size="query.size" layout="total, sizes, prev, pager, next, jumper" :total="count">
			</el-pagination>
		</div>
		<!-- /分页器 -->

	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				// 获取连接地址
				url_get_list: "~/api/user_group/get_list?like=0",
				url_del: "~/api/user_group/del?",

				// 字段ID
				field: "group_id",

				// 查询
				query: {
					size: 10,
					page: 1,
					name: "",
					location:"",
					target:""
				},

				// 数据
				list: [],

			}
		},
		methods:{
			table_class({row, column, rowIndex, columnIndex}){
				return "table_class";
			}
		}
	}
</script>

<style type="text/css">
</style>
