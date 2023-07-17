<template>
  <el-table align="center" :data="tableData"
            @selection-change="selectionChangeHandle"
            >
    <el-table-column prop="narsAvailStr"
                     label="narsAvail" width="200"></el-table-column>
    <el-table-column prop="narsPath"
                     label="narsPath" width="200"></el-table-column>
    <el-table-column prop="narsSizeStr"
                     label="narsSize" width="200"></el-table-column>
    <el-table-column prop="narsUsedStr"
                     label="narsUsed" width="200">
                     <template slot-scope="scope">
                      <span style="margin-right: 10px;width: 30px;">{{ scope.row.narsUsedStr }}</span>
                      <i v-if="scope.row.narsAvail==0" class="el-icon-warning" style="color: rgb(250, 44, 44);font-size:15px;"></i>
                      <i v-if="scope.row.narsAvail>0"  class="el-icon-success" style="color: rgb(39, 207, 39);font-size:15px;"></i>
                    </template>     
                    </el-table-column>
    <el-table-column prop="linkUserName"
                     label="linkUserName" width="200"></el-table-column>
    <el-table-column prop="linkUserSoeid" fixed="left"
                     label="linkUserSoeid" width="200"></el-table-column>
       
   <el-table-column prop="updateTime"
                     label="updateTime" width="200"></el-table-column>
   <el-table-column prop="createTime"
                     label="createTime" width="200"></el-table-column>
                     
<!-- 
   <el-table-column prop="email"
   label="email">
<template slot-scope="scope">
{{ scope.row.time | formatDate('YYYY-MM-DD HH:mm:ss')}}
</template>
</el-table-column> -->
    <el-table-column label="操作"
                     width="200" fixed="right">
      <template slot-scope="scope">
        <el-button type="text"
                   size="small"
                   class="mar-r-20"
                   @click="editHandle(scope.$index, scope.row)">编辑</el-button>
        <el-button type="text"
                   size="small"
                   class="mar-r-20">
          <router-link :to="{path: '/nas/nasList/nasDetail',query: {soeid: scope.row.linkUserSoeid}}">邮件记录</router-link>
        </el-button>
        <el-button type="text"
                   size="small"
                   @click="singleDeleteHandle(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  props: {
    //表格数据
    tableData: {
      type: Array,
      default: Object,
      required: true
    }
  },
  methods: {
    //表格全选
    selectionChangeHandle (data) {
      this.$emit('selectionChangeHandle', data)
    },
    //编辑
    editHandle (index, row) {
      this.$emit('editHandle', index, row)
    },
    //关联用户
    linkUser (index, row) {
      this.$emit('linkUserHandle', index, row)
    },
    //删除
    singleDeleteHandle (index, row) {
      this.$emit('singleDeleteHandle', index, row)
    }
  }
}
</script>

<style>
  .el-table td .el-table th{
    text-align: center !important;
  }


  </style>