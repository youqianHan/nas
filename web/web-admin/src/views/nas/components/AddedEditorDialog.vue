<template>
  <!--新增、编辑-->
  <el-dialog title=""
             :visible.sync="dialogVisible"
             :before-close="handleCancel"
             width="500px">
    <el-form :model="dialogForm"
             ref="dialogForm"
             label-width="100px"
             :rules="formRules">
      <el-form-item prop="narsAvail"
                    label="narsAvail">
        <el-input v-model.trim="dialogForm.narsAvail"
                  maxlength="30"
                  placeholder="narsAvail"
                  style="width: 90%;"></el-input>
      </el-form-item>
      <el-form-item prop="narsPath"
                    label="narsPath">
        <el-input @keyup.enter.native="submitHandle('dialogForm')"
                  v-model.trim="dialogForm.narsPath"
                  placeholder="narsPath"
                  style="width: 90%;"></el-input>
      </el-form-item>
      <el-form-item prop="narsSize"
                    label="narsSize">
        <el-input @keyup.enter.native="submitHandle('dialogForm')"
                  v-model.trim="dialogForm.narsSize"
                  placeholder="narsSize"
                  style="width: 90%;"></el-input>
      </el-form-item>
      <el-form-item prop="narsUsed"
                    label="narsUsed">
        <el-input @keyup.enter.native="submitHandle('dialogForm')"
                  v-model.trim="dialogForm.narsUsed"
                  placeholder="narsUsed"
                  style="width: 90%;"></el-input>
      </el-form-item>
      <el-form-item prop="linkUserId"
                    label="linkUser">
        <el-select filterable v-model="dialogForm.linkUserId" placeholder="请选择">
          <el-option-group
            v-for="group in options"
            :key="group.userGroup"
            :label="`所属组-${group.userGroup}`">
            <el-option
              v-for="item in group.sysUserVos"
              :key="item.value"
              :label="item.userName"
              :value="item.id">
            </el-option>
          </el-option-group>
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer"
         class="dialog-footer">
      <el-button @click="handleCancel">取 消</el-button>
      <el-button type="primary"
                 @click="submitHandle('dialogForm')"
                 :loading="submitLoading">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
export default {
  props: {
    //弹窗是否显示
    dialogVisible: {
      type: Boolean,
      default: false,
      required: true
    },
    //弹窗表单
    dialogForm: {
      type: Object,
      default: Object,
      required: true
    },
    //提交loading
    submitLoading: {
      type: Boolean,
      default: false,
      required: true
    }
  },
  data () {
    let validateText = (rule, value, callback) => {
      let reg = /[^\u4e00-\u9fa5]/;
      if (reg.test(value) || value === "") {
        callback(new Error("请输入中文拍品名称"));
      } else {
        callback();
      }
    };
    return {
      options: [],
        value: '',
      //弹窗应用表单验证规则
      formRules: {
        name: [
          {
            required: true,
            trigger: "blur",
            validator: validateText
          }
        ],
        code: [{ required: true, message: "请输入拍品代码", trigger: "blur" }]
      }
    };
  },
  created () {
    //请求列表
    this.initSelectData()
  },
  methods: {
   async initSelectData(){
      this.loading = true//loading
      let res = await this.$api.systemModule.usersManage.groupUserList()
      let data = res.data.data
      if (res.data.code === this.$config.RET_CODE.SUCCESS_CODE) {
        this.loading = false
        if (data.length > 0) {
            this.options = data;
        } 
      } else {
        this.$message({
          type: 'error',
          message: res.data.message
        })
      }
    },
    //提交
    submitHandle (formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$emit("submitHandle", this.dialogForm)
        }
      })
    },
    //关闭弹窗
    handleCancel () {
      this.$emit("getCancel")
    }
  }
}
</script>
