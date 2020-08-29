// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package web.api.rpc.user;

public final class User {
  private User() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_RegistrationRequestGrpc_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_RegistrationRequestGrpc_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_RegistrationResponseGrpc_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_RegistrationResponseGrpc_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ConfirmEmailRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ConfirmEmailRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ConfirmEmailResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ConfirmEmailResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PasswordResetRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_PasswordResetRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PasswordResetResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_PasswordResetResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_RegistrationInformationRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_RegistrationInformationRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_RegistrationInformationResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_RegistrationInformationResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_LoginRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_LoginRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_LoginResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_LoginResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_NewPasswordRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_NewPasswordRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_NewPasswordResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_NewPasswordResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_VerificationResetPasswordTokenRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_VerificationResetPasswordTokenRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_VerificationResetPasswordTokenResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_VerificationResetPasswordTokenResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GetEmailRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GetEmailRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GetEmailResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GetEmailResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ValidateTokenRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ValidateTokenRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ValidateTokenResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ValidateTokenResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GetAllUserRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GetAllUserRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GetAllUserResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GetAllUserResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GetAllUserResponse_UserGrpcModel_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GetAllUserResponse_UserGrpcModel_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nuser.proto\"l\n\027RegistrationRequestGrpc\022" +
      "\r\n\005email\030\001 \001(\t\022\020\n\010password\030\002 \001(\t\022\021\n\tfirs" +
      "tName\030\003 \001(\t\022\020\n\010lastName\030\004 \001(\t\022\013\n\003sex\030\005 \001" +
      "(\010\"9\n\030RegistrationResponseGrpc\022\r\n\005email\030" +
      "\001 \001(\t\022\016\n\006status\030\002 \001(\t\"$\n\023ConfirmEmailReq" +
      "uest\022\r\n\005token\030\001 \001(\t\"5\n\024ConfirmEmailRespo" +
      "nse\022\016\n\006status\030\001 \001(\t\022\r\n\005email\030\002 \001(\t\"%\n\024Pa" +
      "sswordResetRequest\022\r\n\005email\030\001 \001(\t\"6\n\025Pas" +
      "swordResetResponse\022\016\n\006status\030\001 \001(\t\022\r\n\005em" +
      "ail\030\002 \001(\t\"P\n\036RegistrationInformationRequ" +
      "est\022\r\n\005email\030\001 \001(\t\022\020\n\010userName\030\002 \001(\t\022\r\n\005" +
      "phone\030\003 \001(\t\"1\n\037RegistrationInformationRe" +
      "sponse\022\016\n\006status\030\001 \001(\t\"/\n\014LoginRequest\022\r" +
      "\n\005email\030\001 \001(\t\022\020\n\010password\030\002 \001(\t\"P\n\rLogin" +
      "Response\022\016\n\006status\030\001 \001(\t\022\r\n\005token\030\002 \001(\t\022" +
      "\020\n\010username\030\003 \001(\t\022\016\n\006userId\030\004 \001(\t\"T\n\022New" +
      "PasswordRequest\022\r\n\005token\030\001 \001(\t\022\023\n\013newPas" +
      "sword\030\002 \001(\t\022\032\n\022newPasswordConfirm\030\003 \001(\t\"" +
      "4\n\023NewPasswordResponse\022\r\n\005email\030\001 \001(\t\022\016\n" +
      "\006status\030\002 \001(\t\"6\n%VerificationResetPasswo" +
      "rdTokenRequest\022\r\n\005token\030\001 \001(\t\"G\n&Verific" +
      "ationResetPasswordTokenResponse\022\r\n\005email" +
      "\030\001 \001(\t\022\016\n\006status\030\002 \001(\t\" \n\017GetEmailReques" +
      "t\022\r\n\005token\030\001 \001(\t\"!\n\020GetEmailResponse\022\r\n\005" +
      "email\030\001 \001(\t\"4\n\024ValidateTokenRequest\022\r\n\005e" +
      "mail\030\001 \001(\t\022\r\n\005token\030\002 \001(\t\"\'\n\025ValidateTok" +
      "enResponse\022\016\n\006status\030\001 \001(\010\"!\n\021GetAllUser" +
      "Request\022\014\n\004page\030\001 \001(\005\"\212\001\n\022GetAllUserResp" +
      "onse\0222\n\007allUser\030\001 \003(\0132!.GetAllUserRespon" +
      "se.UserGrpcModel\032@\n\rUserGrpcModel\022\n\n\002id\030" +
      "\001 \001(\t\022\021\n\tfirstName\030\002 \001(\t\022\020\n\010lastName\030\003 \001" +
      "(\t2\307\005\n\013UserService\022C\n\014Registration\022\030.Reg" +
      "istrationRequestGrpc\032\031.RegistrationRespo" +
      "nseGrpc\022&\n\005Login\022\r.LoginRequest\032\016.LoginR" +
      "esponse\022L\n\035VerificationTokenRegistration" +
      "\022\024.ConfirmEmailRequest\032\025.ConfirmEmailRes" +
      "ponse\022?\n\016PasswordForgot\022\025.PasswordResetR" +
      "equest\032\026.PasswordResetResponse\022:\n\rPasswo" +
      "rdReset\022\023.NewPasswordRequest\032\024.NewPasswo" +
      "rdResponse\022\\\n\027RegistrationInformation\022\037." +
      "RegistrationInformationRequest\032 .Registr" +
      "ationInformationResponse\022q\n\036Verification" +
      "ResetPasswordToken\022&.VerificationResetPa" +
      "sswordTokenRequest\032\'.VerificationResetPa" +
      "sswordTokenResponse\0228\n\021GetEmailFromToken" +
      "\022\020.GetEmailRequest\032\021.GetEmailResponse\022>\n" +
      "\rValidateToken\022\025.ValidateTokenRequest\032\026." +
      "ValidateTokenResponse\0225\n\nGetAllUser\022\022.Ge" +
      "tAllUserRequest\032\023.GetAllUserResponseB\027\n\020" +
      "web.api.rpc.userP\001\210\001\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_RegistrationRequestGrpc_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_RegistrationRequestGrpc_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_RegistrationRequestGrpc_descriptor,
        new java.lang.String[] { "Email", "Password", "FirstName", "LastName", "Sex", });
    internal_static_RegistrationResponseGrpc_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_RegistrationResponseGrpc_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_RegistrationResponseGrpc_descriptor,
        new java.lang.String[] { "Email", "Status", });
    internal_static_ConfirmEmailRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_ConfirmEmailRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ConfirmEmailRequest_descriptor,
        new java.lang.String[] { "Token", });
    internal_static_ConfirmEmailResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_ConfirmEmailResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ConfirmEmailResponse_descriptor,
        new java.lang.String[] { "Status", "Email", });
    internal_static_PasswordResetRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_PasswordResetRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_PasswordResetRequest_descriptor,
        new java.lang.String[] { "Email", });
    internal_static_PasswordResetResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_PasswordResetResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_PasswordResetResponse_descriptor,
        new java.lang.String[] { "Status", "Email", });
    internal_static_RegistrationInformationRequest_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_RegistrationInformationRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_RegistrationInformationRequest_descriptor,
        new java.lang.String[] { "Email", "UserName", "Phone", });
    internal_static_RegistrationInformationResponse_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_RegistrationInformationResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_RegistrationInformationResponse_descriptor,
        new java.lang.String[] { "Status", });
    internal_static_LoginRequest_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_LoginRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_LoginRequest_descriptor,
        new java.lang.String[] { "Email", "Password", });
    internal_static_LoginResponse_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_LoginResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_LoginResponse_descriptor,
        new java.lang.String[] { "Status", "Token", "Username", "UserId", });
    internal_static_NewPasswordRequest_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_NewPasswordRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_NewPasswordRequest_descriptor,
        new java.lang.String[] { "Token", "NewPassword", "NewPasswordConfirm", });
    internal_static_NewPasswordResponse_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_NewPasswordResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_NewPasswordResponse_descriptor,
        new java.lang.String[] { "Email", "Status", });
    internal_static_VerificationResetPasswordTokenRequest_descriptor =
      getDescriptor().getMessageTypes().get(12);
    internal_static_VerificationResetPasswordTokenRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_VerificationResetPasswordTokenRequest_descriptor,
        new java.lang.String[] { "Token", });
    internal_static_VerificationResetPasswordTokenResponse_descriptor =
      getDescriptor().getMessageTypes().get(13);
    internal_static_VerificationResetPasswordTokenResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_VerificationResetPasswordTokenResponse_descriptor,
        new java.lang.String[] { "Email", "Status", });
    internal_static_GetEmailRequest_descriptor =
      getDescriptor().getMessageTypes().get(14);
    internal_static_GetEmailRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GetEmailRequest_descriptor,
        new java.lang.String[] { "Token", });
    internal_static_GetEmailResponse_descriptor =
      getDescriptor().getMessageTypes().get(15);
    internal_static_GetEmailResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GetEmailResponse_descriptor,
        new java.lang.String[] { "Email", });
    internal_static_ValidateTokenRequest_descriptor =
      getDescriptor().getMessageTypes().get(16);
    internal_static_ValidateTokenRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ValidateTokenRequest_descriptor,
        new java.lang.String[] { "Email", "Token", });
    internal_static_ValidateTokenResponse_descriptor =
      getDescriptor().getMessageTypes().get(17);
    internal_static_ValidateTokenResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ValidateTokenResponse_descriptor,
        new java.lang.String[] { "Status", });
    internal_static_GetAllUserRequest_descriptor =
      getDescriptor().getMessageTypes().get(18);
    internal_static_GetAllUserRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GetAllUserRequest_descriptor,
        new java.lang.String[] { "Page", });
    internal_static_GetAllUserResponse_descriptor =
      getDescriptor().getMessageTypes().get(19);
    internal_static_GetAllUserResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GetAllUserResponse_descriptor,
        new java.lang.String[] { "AllUser", });
    internal_static_GetAllUserResponse_UserGrpcModel_descriptor =
      internal_static_GetAllUserResponse_descriptor.getNestedTypes().get(0);
    internal_static_GetAllUserResponse_UserGrpcModel_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GetAllUserResponse_UserGrpcModel_descriptor,
        new java.lang.String[] { "Id", "FirstName", "LastName", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
