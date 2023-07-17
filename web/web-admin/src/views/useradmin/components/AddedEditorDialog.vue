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
      <el-form-item prop="userName"
                    label="用户名">
        <el-input v-model.trim="dialogForm.userName"
                  maxlength="30"
                  placeholder="用户名"
                  style="width: 90%;"></el-input>
      </el-form-item>
      <el-form-item prop="soeid"
                    label="soeid">
        <el-input @keyup.enter.native="submitHandle('dialogForm')"
                  v-model.trim="dialogForm.soeid"
                  placeholder="soeid"
                  style="width: 90%;"></el-input>
      </el-form-item>
      <el-form-item prop="email"
                    label="email">
        <el-input @keyup.enter.native="submitHandle('dialogForm')"
                  v-model.trim="dialogForm.email"
                  placeholder="email"
                  style="width: 90%;"></el-input>
      </el-form-item>
            <el-form-item prop="userGroup"
                    label="userGroup">
        <el-input @keyup.enter.native="submitHandle('dialogForm')"
                  v-model.trim="dialogForm.userGroup"
                  placeholder="userGroup"
                  style="width: 90%;"></el-input>
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
  methods: {
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
