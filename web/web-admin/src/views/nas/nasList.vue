<template>
  <section>
    <section v-loading="loading"
             element-loading-text="拼命加载中……"
             class="bg-white">
      <div class="table-filter-wrapper mar-l-10">
        <ph-form @searchBtn="searchBtn"
                 @resetSearchBtn="resetSearchBtn"
                 @addHandle="addHandle"
                 :searchForm="searchForm">
        </ph-form>
      </div>
      <div class="table-wrapper pad-t-5 bor-t-1">
        <ph-table :tableData="tableData"
                  @editHandle="editHandle"
                  @singleDeleteHandle="singleDeleteHandle"
                  @selectionChangeHandle="selectionChangeHandle">
        </ph-table>
        <el-row class="mar-t-10">
          <el-col :span='4'>
            <el-button @click="batchDeleteHandle()"
                       :disabled="isBatchDeleteDisabled"
                       size="small">批量删除
            </el-button>
          </el-col>
          <el-col :span='20'>
            <ph-pagination @sendPaginations="sendPaginationsHandle"
                           :paginations="paginationParams"
                           class="mar-r-20 text-right">
            </ph-pagination>
          </el-col>
        </el-row>
      </div>
    </section>
    <!--新增、编辑-->
    <added-editor-dialog :dialogForm="dialogForm"
                         :submitLoading="submitLoading"
                         :dialogVisible.sync="addedEditorDialog"
                         @getCancel="handleCancel"
                         @submitHandle="submitAddedEditorForm">
    </added-editor-dialog>            
  </section>
</template>

<script>
import Form from './Form'
import Table from './Table'
import Pagination from '@/components/Pagination'
import AddedEditorDialog from './components/AddedEditorDialog'
export default {
  name: 'goodsList',
  components: {
    'ph-pagination': Pagination,
    'added-editor-dialog': AddedEditorDialog,
    'ph-form': Form,
    'ph-table': Table
  },
  data () {
    return {
      loading: false,//loading
      submitLoading: false,//弹窗提交loading
      paginationParams: this.$config.paginationParams,//列表分页
      handleType: '',//判断弹窗提交是新增还是编辑
      checkedIds: [],//checkbox被选中的子项
      isBatchDeleteDisabled: false,//是否可以批量删除
      //table数据
      tableData: [],
      //弹窗
      addedEditorDialog: false,
      linkUserDialog: false,
      dialogForm: {
        narsPath: '',
        narsSize: '',
        id: '',
        narsUsed: '',
        narsAvail:'',
        linkUserId: Number
      },
      //搜索
      searchForm: {}
    }
  },
  created () {
    //请求列表
    this.getList()
  },
  methods: {
    /*
      *functionName: successList
      * params: params Object
      * params: id String
      * desc:封装初始化获取列表和搜索  共用
    */
    async successList (params) {
      this.loading = true//loading
      let res = await this.$api.systemModule.nasManage.getList(params)
      let data = res.data.data
      debugger
      if (res.data.code === this.$config.RET_CODE.SUCCESS_CODE) {
        this.loading = false
        if (data.records.length > 0) {
          this.isBatchDeleteDisabled = false//启用批量删除
          this.tableData = data.records//存储到tableData
          // this.paginationParams.pageSize = data.pageSize//每页的数量
          this.paginationParams.size = data.size//当前页的数量
          this.paginationParams.total = data.total//总条数
          this.paginationParams.pages = data.pages//总页码数
        } else {
          this.tableData = []
          this.paginationParams.total = 0
          this.isBatchDeleteDisabled = true//禁用批量删除
        }
      } else {
        this.$message({
          type: 'error',
          message: res.data.message
        })
      }
    },
    /*
      *functionName: getList
      * params: params Object
      * params: aliveFlag String
      * params: pageNo Number
      * params: pageSize Number
      * desc:获取表格数据
    */
    getList () {
	  // return this.$message.error("请配置接口!");
      let params = {
        pageNo: this.paginationParams.pageNo,//页码
        pageSize: this.paginationParams.pageSize//每页条数
      }
      //列表成功请求
      this.successList(params)
    },
    /*
      *functionName: searchBtn
      * params: searchData Object
      * desc:查询
    */
    searchBtn (searchData) {
      debugger
      searchData.pageNo =this.paginationParams.pageNo,//页码
      searchData.pageSize = this.paginationParams.pageSize,//每页条数
      // let params = {
      //   pageNo: this.paginationParams.pageNo,//页码
      //   pageSize: this.paginationParams.pageSize,//每页条数

      //   name: searchData && searchData.name ? searchData.name : null,
      // }
      //列表成功请求
      this.successList(searchData)
    },
    /*
      *functionName: sendPaginationsHandle
      * params: params Object
      * desc:接收分页参数
    */
    sendPaginationsHandle () {
      debugger
        let params = {
          pageNo: this.paginationParams.pageNo,//页码
          pageSize: this.paginationParams.pageSize}//每页条数
        //发送请求
        this.successList(params)

    },
    /*
      *functionName: resetSearchBtn
      * desc:重置搜索查询
    */
    resetSearchBtn () {
      this.searchForm = {}
      this.paginationParams.pageNo = 1
      this.paginationParams.pageSize = 5
      this.getList()
    },
    /*
      *functionName: addHandle
      * desc:弹出新增
    */
    addHandle () {
      this.handleType = 'add'//判断为新增
      this.addedEditorDialog = true//显示弹窗
      //设置传给后台的数据
      this.dialogForm = {
        name: '',
        code: ''
      }
    },
    /*
      *functionName: selectionChangeHandle
      * params: checkedData Object
      * desc:表格全选
    */
    selectionChangeHandle (checkedData) {
      //checkedData是选中的数组，遍历后的数据为数组，如[1,2,3]，追加到checkedIds作为传给后台的参数
      checkedData.forEach(item => {
        this.checkedIds.push(item.id)
      })
    },
    /*
      *functionName: editHandle
      * params: params Number
      * desc:弹出修改
    */
    editHandle (index, row) {
      debugger
      this.handleType = 'edit'//判断为编辑
      this.addedEditorDialog = true//显示弹窗
      Object.assign(this.dialogForm, row);
      //请求接口
      let params = row.id
	  // return this.$message.error("请配置接口!");
      this.$api.systemModule.nasManage.getDataById(params)
        .then(res => {
          if (res.data.code === this.$config.RET_CODE.SUCCESS_CODE) {
            let data = res.data.data
            //设置传给后台的数据
            Object.assign(this.dialogForm, data);
          } else {
            this.$message({
              soeid: 'error',
              message: res.data.message
            })
          }
        }).catch(err => {
          console.log(err)
        })
    },
        /*
      *functionName: editHandle
      * params: params Number
      * desc:弹出关联user
    */
    linkUserHandle (index, row) {
      debugger
      this.handleType = 'edit'//判断为编辑
      this.addedEditorDialog = true//显示弹窗
      Object.assign(this.dialogForm, row);
      //请求接口
      let params = row.id
	  // return this.$message.error("请配置接口!");
      this.$api.systemModule.nasManage.getDataById(params)
        .then(res => {
          if (res.data.code === this.$config.RET_CODE.SUCCESS_CODE) {
            let data = res.data.data
            //设置传给后台的数据
            Object.assign(this.dialogForm, data);
          } else {
            this.$message({
              soeid: 'error',
              message: res.data.message
            })
          }
        }).catch(err => {
          console.log(err)
        })
    },
    /*
      *functionName: deleteHandle
      * params: params Object
      * desc:删除共用方法
    */
    deleteHandle (tipsText, params, cancelTips) {
      //提示框
      debugger
      this.$confirm(tipsText, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
      }).then(() => {
        //请求删除账号接口
        this.$api.systemModule.nasManage.delete(params.ids)
          .then(res => {
            if (res.data.code === this.$config.RET_CODE.SUCCESS_CODE) {
              this.$message({
                type: 'success',
                message: res.data.message
              })
              this.getList()
            } else {
              this.$message({
                type: 'error',
                message: res.data.message
              })
            }
          }).catch(err => {
            console.log(err)
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: cancelTips
        })
      })
    },
    /*
      *functionName: singleDeleteHandle
      * params: params Object
      * desc:单个删除
    */
    singleDeleteHandle (index, row) {
      let arr = []
      arr.push(row.id)
      let params = {
        ids: [row.id]//数组
      }
      this.deleteHandle('确定删除当前nas信息?', params, '取消删除')
    },
    /*
      *functionName: batchDeleteHandle
      * params: params Array
      * desc:批量删除
    */
    batchDeleteHandle () {
      if (this.checkedIds.length > 0) {
        let params = {
          ids: this.checkedIds//数组
        }
        this.deleteHandle('确定批量删除当前拍品信息?', params, '取消删除')
      } else {
        this.$message({
          type: 'error',
          message: '请选择拍品'
        })
      }
    },
    /*
      *functionName: handleCancel
      * desc:点击弹框下的取消
    */
    handleCancel () {
      this.addedEditorDialog = false
    },
    /*
      *functionName: submitAddedEditorForm
      * params: params Array
      * desc:新增、修改弹窗提交
    */
    submitAddedEditorForm () {
      debugger
      this.submitLoading = true
      let params = this.dialogForm
      if (this.handleType === 'add') {
        //添加
        this.$api.systemModule.nasManage.addNas(params)
          .then(res => {
            if (res.data.code === this.$config.RET_CODE.SUCCESS_CODE) {
              this.submitLoading = false
              this.$message({
                message: res.data.message,
                type: "success"
              })
              this.getList()
              this.addedEditorDialog = false//隐藏弹窗
            } else {
              //账号已存在或者其他异常
              this.$message({
                message: res.data.message,
                type: "error"
              })
              this.submitLoading = false
            }
          }).catch(err => {
            console.log(err)
            this.addedEditorDialog = false//隐藏弹窗
            this.submitLoading = false
          })
      } else if (this.handleType === 'edit') {
        //编辑
        debugger
        this.$api.systemModule.nasManage.edit(params)
          .then(res => {
            if (res.data.code === this.$config.RET_CODE.SUCCESS_CODE) {
              this.submitLoading = false
              this.$message({
                type: "success",
                message: res.data.message
              })
              this.getList()//再次请求列表
              this.addedEditorDialog = false//隐藏弹窗
            } else {
              //其他异常
              this.$message({
                type: "error",
                message: res.data.message
              })
              this.submitLoading = false
            }
          }).catch(err => {
            //隐藏弹窗
            console.log(err)
            this.addedEditorDialog = false
            this.submitLoading = false
          })
      }
    }
  }
}
</script>
