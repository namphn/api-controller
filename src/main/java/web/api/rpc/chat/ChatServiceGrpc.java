package web.api.rpc.chat;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.16.1)",
    comments = "Source: chat.proto")
public final class ChatServiceGrpc {

  private ChatServiceGrpc() {}

  public static final String SERVICE_NAME = "ChatService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<web.api.rpc.chat.GetChannelRequest,
      web.api.rpc.chat.GetChannelResponse> getGetChannelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getChannel",
      requestType = web.api.rpc.chat.GetChannelRequest.class,
      responseType = web.api.rpc.chat.GetChannelResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<web.api.rpc.chat.GetChannelRequest,
      web.api.rpc.chat.GetChannelResponse> getGetChannelMethod() {
    io.grpc.MethodDescriptor<web.api.rpc.chat.GetChannelRequest, web.api.rpc.chat.GetChannelResponse> getGetChannelMethod;
    if ((getGetChannelMethod = ChatServiceGrpc.getGetChannelMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getGetChannelMethod = ChatServiceGrpc.getGetChannelMethod) == null) {
          ChatServiceGrpc.getGetChannelMethod = getGetChannelMethod = 
              io.grpc.MethodDescriptor.<web.api.rpc.chat.GetChannelRequest, web.api.rpc.chat.GetChannelResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "ChatService", "getChannel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  web.api.rpc.chat.GetChannelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  web.api.rpc.chat.GetChannelResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("getChannel"))
                  .build();
          }
        }
     }
     return getGetChannelMethod;
  }

  private static volatile io.grpc.MethodDescriptor<web.api.rpc.chat.ChatMessage,
      web.api.rpc.chat.SubmitResponse> getSubmitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "submit",
      requestType = web.api.rpc.chat.ChatMessage.class,
      responseType = web.api.rpc.chat.SubmitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<web.api.rpc.chat.ChatMessage,
      web.api.rpc.chat.SubmitResponse> getSubmitMethod() {
    io.grpc.MethodDescriptor<web.api.rpc.chat.ChatMessage, web.api.rpc.chat.SubmitResponse> getSubmitMethod;
    if ((getSubmitMethod = ChatServiceGrpc.getSubmitMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getSubmitMethod = ChatServiceGrpc.getSubmitMethod) == null) {
          ChatServiceGrpc.getSubmitMethod = getSubmitMethod = 
              io.grpc.MethodDescriptor.<web.api.rpc.chat.ChatMessage, web.api.rpc.chat.SubmitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "ChatService", "submit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  web.api.rpc.chat.ChatMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  web.api.rpc.chat.SubmitResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("submit"))
                  .build();
          }
        }
     }
     return getSubmitMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ChatServiceStub newStub(io.grpc.Channel channel) {
    return new ChatServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ChatServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ChatServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ChatServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ChatServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ChatServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getChannel(web.api.rpc.chat.GetChannelRequest request,
        io.grpc.stub.StreamObserver<web.api.rpc.chat.GetChannelResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetChannelMethod(), responseObserver);
    }

    /**
     */
    public void submit(web.api.rpc.chat.ChatMessage request,
        io.grpc.stub.StreamObserver<web.api.rpc.chat.SubmitResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubmitMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetChannelMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                web.api.rpc.chat.GetChannelRequest,
                web.api.rpc.chat.GetChannelResponse>(
                  this, METHODID_GET_CHANNEL)))
          .addMethod(
            getSubmitMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                web.api.rpc.chat.ChatMessage,
                web.api.rpc.chat.SubmitResponse>(
                  this, METHODID_SUBMIT)))
          .build();
    }
  }

  /**
   */
  public static final class ChatServiceStub extends io.grpc.stub.AbstractStub<ChatServiceStub> {
    private ChatServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceStub(channel, callOptions);
    }

    /**
     */
    public void getChannel(web.api.rpc.chat.GetChannelRequest request,
        io.grpc.stub.StreamObserver<web.api.rpc.chat.GetChannelResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetChannelMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void submit(web.api.rpc.chat.ChatMessage request,
        io.grpc.stub.StreamObserver<web.api.rpc.chat.SubmitResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubmitMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ChatServiceBlockingStub extends io.grpc.stub.AbstractStub<ChatServiceBlockingStub> {
    private ChatServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public web.api.rpc.chat.GetChannelResponse getChannel(web.api.rpc.chat.GetChannelRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetChannelMethod(), getCallOptions(), request);
    }

    /**
     */
    public web.api.rpc.chat.SubmitResponse submit(web.api.rpc.chat.ChatMessage request) {
      return blockingUnaryCall(
          getChannel(), getSubmitMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ChatServiceFutureStub extends io.grpc.stub.AbstractStub<ChatServiceFutureStub> {
    private ChatServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<web.api.rpc.chat.GetChannelResponse> getChannel(
        web.api.rpc.chat.GetChannelRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetChannelMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<web.api.rpc.chat.SubmitResponse> submit(
        web.api.rpc.chat.ChatMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getSubmitMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_CHANNEL = 0;
  private static final int METHODID_SUBMIT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ChatServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ChatServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_CHANNEL:
          serviceImpl.getChannel((web.api.rpc.chat.GetChannelRequest) request,
              (io.grpc.stub.StreamObserver<web.api.rpc.chat.GetChannelResponse>) responseObserver);
          break;
        case METHODID_SUBMIT:
          serviceImpl.submit((web.api.rpc.chat.ChatMessage) request,
              (io.grpc.stub.StreamObserver<web.api.rpc.chat.SubmitResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ChatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ChatServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return web.api.rpc.chat.Chat.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ChatService");
    }
  }

  private static final class ChatServiceFileDescriptorSupplier
      extends ChatServiceBaseDescriptorSupplier {
    ChatServiceFileDescriptorSupplier() {}
  }

  private static final class ChatServiceMethodDescriptorSupplier
      extends ChatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ChatServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ChatServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ChatServiceFileDescriptorSupplier())
              .addMethod(getGetChannelMethod())
              .addMethod(getSubmitMethod())
              .build();
        }
      }
    }
    return result;
  }
}
