<template>
  <section>
    <section v-loading="loading"
             element-loading-text="拼命加载中……"
             class="bg-white">
    <div class="text-center mar-t-30">
      <template>
        <el-button type="default" @click="back" round>返回上级页面</el-button>

        <el-table
          :data="tableData"
          height="800"
          style="width: 100%">
          <el-table-column
            prop="soeid"
            label="soeid"
            width="180">
          </el-table-column>
          <el-table-column
            prop="sendTo"
            label="sendTo"
            width="180">
          </el-table-column>
          <el-table-column
            prop="subject"
            label="subject">
          </el-table-column>
          <el-table-column
            prop="content"
            label="content">
          </el-table-column>
          <el-table-column
            prop="sendTime"
            label="sendTime">
          </el-table-column>
        </el-table>
      </template>
    </div>
    </section>
  </section>
</template>

<script>

export default {
  name: 'goodsDetail',
  data () {
    return {
      loading: false,//loading
      soeid: this.$route.query.soeid,
      tableData: []
    }
  },
  created () {
    //请求列表
    this.getList(this.soeid)
  },
  methods: {
    //返回列表
    back () {
      this.$router.go(-1);
    },
    //根据soeid查询邮件发送记录
    async getList (soeid) {
      this.loading = true//loading
      debugger
      let res = await this.$api.systemModule.nasManage.querySendEmailFlowBySoeid(soeid)
      let data = res.data.data
      if (res.data.code === this.$config.RET_CODE.SUCCESS_CODE) {
        this.loading = false
        debugger
        if (data.length > 0) {
          this.tableData = data//存储到tableData
        } 
      } else {
        this.$message({
          type: 'error',
          message: res.data.message
        })
      }
    },
  }
}
</script>


