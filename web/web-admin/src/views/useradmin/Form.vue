<template>
  <section class="search-form-wrapper">
    <el-form :inline="true">
      <el-row :gutter="20">
        <el-col :span="20">
          <div class="fl">
            <el-form-item label="userName"
                          class="pad-r-20">
              <el-input placeholder="请输入名称"
                        v-model.trim="searchForm.userName"></el-input>
            </el-form-item>
            <el-form-item label="soeid"
                          class="pad-r-20">
              <el-input placeholder="请输入soeid"
                        v-model.trim="searchForm.soeid"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="searchBtn()"
                         type="primary"
                         size="mini">查询</el-button>
              <el-button @click="resetSearchBtn()"
                         size="mini">重置</el-button>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="4"
                class="text-right">
          <el-button type="primary"
                     round
                     @click="addHandle()">添加用户</el-button>
        </el-col>
      </el-row>
    </el-form>
  </section>
</template>

<script>
export default {
  props: {
    //搜索
    searchForm: {
      type: Object,
      default: Object,
      required: true
    }
  },
  methods: {
    //查询
    searchBtn () {
      this.$emit("searchBtn", this.searchForm);
    },
    //重置
    resetSearchBtn () {
      this.$emit("resetSearchBtn");
    },
    //添加
    addHandle () {
      this.$emit("addHandle");
    }
  }
};
</script>
