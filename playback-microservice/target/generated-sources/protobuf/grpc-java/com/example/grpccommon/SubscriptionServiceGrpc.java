package com.example.grpccommon;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.60.0)",
    comments = "Source: common.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SubscriptionServiceGrpc {

  private SubscriptionServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "service.SubscriptionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.grpccommon.CreateSubscriptionRequest,
      com.example.grpccommon.CreateSubscriptionResponse> getCreateSubscriptionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateSubscription",
      requestType = com.example.grpccommon.CreateSubscriptionRequest.class,
      responseType = com.example.grpccommon.CreateSubscriptionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpccommon.CreateSubscriptionRequest,
      com.example.grpccommon.CreateSubscriptionResponse> getCreateSubscriptionMethod() {
    io.grpc.MethodDescriptor<com.example.grpccommon.CreateSubscriptionRequest, com.example.grpccommon.CreateSubscriptionResponse> getCreateSubscriptionMethod;
    if ((getCreateSubscriptionMethod = SubscriptionServiceGrpc.getCreateSubscriptionMethod) == null) {
      synchronized (SubscriptionServiceGrpc.class) {
        if ((getCreateSubscriptionMethod = SubscriptionServiceGrpc.getCreateSubscriptionMethod) == null) {
          SubscriptionServiceGrpc.getCreateSubscriptionMethod = getCreateSubscriptionMethod =
              io.grpc.MethodDescriptor.<com.example.grpccommon.CreateSubscriptionRequest, com.example.grpccommon.CreateSubscriptionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateSubscription"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpccommon.CreateSubscriptionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpccommon.CreateSubscriptionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SubscriptionServiceMethodDescriptorSupplier("CreateSubscription"))
              .build();
        }
      }
    }
    return getCreateSubscriptionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpccommon.LatestSubscriptionRequest,
      com.example.grpccommon.LatestSubscriptionResponse> getGetLatestSubscriptionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetLatestSubscription",
      requestType = com.example.grpccommon.LatestSubscriptionRequest.class,
      responseType = com.example.grpccommon.LatestSubscriptionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpccommon.LatestSubscriptionRequest,
      com.example.grpccommon.LatestSubscriptionResponse> getGetLatestSubscriptionMethod() {
    io.grpc.MethodDescriptor<com.example.grpccommon.LatestSubscriptionRequest, com.example.grpccommon.LatestSubscriptionResponse> getGetLatestSubscriptionMethod;
    if ((getGetLatestSubscriptionMethod = SubscriptionServiceGrpc.getGetLatestSubscriptionMethod) == null) {
      synchronized (SubscriptionServiceGrpc.class) {
        if ((getGetLatestSubscriptionMethod = SubscriptionServiceGrpc.getGetLatestSubscriptionMethod) == null) {
          SubscriptionServiceGrpc.getGetLatestSubscriptionMethod = getGetLatestSubscriptionMethod =
              io.grpc.MethodDescriptor.<com.example.grpccommon.LatestSubscriptionRequest, com.example.grpccommon.LatestSubscriptionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetLatestSubscription"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpccommon.LatestSubscriptionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpccommon.LatestSubscriptionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SubscriptionServiceMethodDescriptorSupplier("GetLatestSubscription"))
              .build();
        }
      }
    }
    return getGetLatestSubscriptionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SubscriptionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SubscriptionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SubscriptionServiceStub>() {
        @java.lang.Override
        public SubscriptionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SubscriptionServiceStub(channel, callOptions);
        }
      };
    return SubscriptionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SubscriptionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SubscriptionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SubscriptionServiceBlockingStub>() {
        @java.lang.Override
        public SubscriptionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SubscriptionServiceBlockingStub(channel, callOptions);
        }
      };
    return SubscriptionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SubscriptionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SubscriptionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SubscriptionServiceFutureStub>() {
        @java.lang.Override
        public SubscriptionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SubscriptionServiceFutureStub(channel, callOptions);
        }
      };
    return SubscriptionServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createSubscription(com.example.grpccommon.CreateSubscriptionRequest request,
        io.grpc.stub.StreamObserver<com.example.grpccommon.CreateSubscriptionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateSubscriptionMethod(), responseObserver);
    }

    /**
     */
    default void getLatestSubscription(com.example.grpccommon.LatestSubscriptionRequest request,
        io.grpc.stub.StreamObserver<com.example.grpccommon.LatestSubscriptionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetLatestSubscriptionMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service SubscriptionService.
   */
  public static abstract class SubscriptionServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return SubscriptionServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service SubscriptionService.
   */
  public static final class SubscriptionServiceStub
      extends io.grpc.stub.AbstractAsyncStub<SubscriptionServiceStub> {
    private SubscriptionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SubscriptionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SubscriptionServiceStub(channel, callOptions);
    }

    /**
     */
    public void createSubscription(com.example.grpccommon.CreateSubscriptionRequest request,
        io.grpc.stub.StreamObserver<com.example.grpccommon.CreateSubscriptionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateSubscriptionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getLatestSubscription(com.example.grpccommon.LatestSubscriptionRequest request,
        io.grpc.stub.StreamObserver<com.example.grpccommon.LatestSubscriptionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetLatestSubscriptionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service SubscriptionService.
   */
  public static final class SubscriptionServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SubscriptionServiceBlockingStub> {
    private SubscriptionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SubscriptionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SubscriptionServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpccommon.CreateSubscriptionResponse createSubscription(com.example.grpccommon.CreateSubscriptionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateSubscriptionMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpccommon.LatestSubscriptionResponse getLatestSubscription(com.example.grpccommon.LatestSubscriptionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetLatestSubscriptionMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service SubscriptionService.
   */
  public static final class SubscriptionServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<SubscriptionServiceFutureStub> {
    private SubscriptionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SubscriptionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SubscriptionServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpccommon.CreateSubscriptionResponse> createSubscription(
        com.example.grpccommon.CreateSubscriptionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateSubscriptionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpccommon.LatestSubscriptionResponse> getLatestSubscription(
        com.example.grpccommon.LatestSubscriptionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetLatestSubscriptionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_SUBSCRIPTION = 0;
  private static final int METHODID_GET_LATEST_SUBSCRIPTION = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_SUBSCRIPTION:
          serviceImpl.createSubscription((com.example.grpccommon.CreateSubscriptionRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpccommon.CreateSubscriptionResponse>) responseObserver);
          break;
        case METHODID_GET_LATEST_SUBSCRIPTION:
          serviceImpl.getLatestSubscription((com.example.grpccommon.LatestSubscriptionRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpccommon.LatestSubscriptionResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCreateSubscriptionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.grpccommon.CreateSubscriptionRequest,
              com.example.grpccommon.CreateSubscriptionResponse>(
                service, METHODID_CREATE_SUBSCRIPTION)))
        .addMethod(
          getGetLatestSubscriptionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.grpccommon.LatestSubscriptionRequest,
              com.example.grpccommon.LatestSubscriptionResponse>(
                service, METHODID_GET_LATEST_SUBSCRIPTION)))
        .build();
  }

  private static abstract class SubscriptionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SubscriptionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpccommon.GeneratorProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SubscriptionService");
    }
  }

  private static final class SubscriptionServiceFileDescriptorSupplier
      extends SubscriptionServiceBaseDescriptorSupplier {
    SubscriptionServiceFileDescriptorSupplier() {}
  }

  private static final class SubscriptionServiceMethodDescriptorSupplier
      extends SubscriptionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    SubscriptionServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (SubscriptionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SubscriptionServiceFileDescriptorSupplier())
              .addMethod(getCreateSubscriptionMethod())
              .addMethod(getGetLatestSubscriptionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
