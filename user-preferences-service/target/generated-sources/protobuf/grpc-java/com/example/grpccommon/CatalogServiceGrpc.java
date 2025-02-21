package com.example.grpccommon;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.60.0)",
    comments = "Source: common.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CatalogServiceGrpc {

  private CatalogServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "catalog.CatalogService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.grpccommon.GetAlbumRequest,
      com.example.grpccommon.GetAlbumResponse> getGetAlbumDetailsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAlbumDetails",
      requestType = com.example.grpccommon.GetAlbumRequest.class,
      responseType = com.example.grpccommon.GetAlbumResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpccommon.GetAlbumRequest,
      com.example.grpccommon.GetAlbumResponse> getGetAlbumDetailsMethod() {
    io.grpc.MethodDescriptor<com.example.grpccommon.GetAlbumRequest, com.example.grpccommon.GetAlbumResponse> getGetAlbumDetailsMethod;
    if ((getGetAlbumDetailsMethod = CatalogServiceGrpc.getGetAlbumDetailsMethod) == null) {
      synchronized (CatalogServiceGrpc.class) {
        if ((getGetAlbumDetailsMethod = CatalogServiceGrpc.getGetAlbumDetailsMethod) == null) {
          CatalogServiceGrpc.getGetAlbumDetailsMethod = getGetAlbumDetailsMethod =
              io.grpc.MethodDescriptor.<com.example.grpccommon.GetAlbumRequest, com.example.grpccommon.GetAlbumResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAlbumDetails"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpccommon.GetAlbumRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpccommon.GetAlbumResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CatalogServiceMethodDescriptorSupplier("GetAlbumDetails"))
              .build();
        }
      }
    }
    return getGetAlbumDetailsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpccommon.GetArtistRequest,
      com.example.grpccommon.GetArtistResponse> getGetArtistDetailsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetArtistDetails",
      requestType = com.example.grpccommon.GetArtistRequest.class,
      responseType = com.example.grpccommon.GetArtistResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpccommon.GetArtistRequest,
      com.example.grpccommon.GetArtistResponse> getGetArtistDetailsMethod() {
    io.grpc.MethodDescriptor<com.example.grpccommon.GetArtistRequest, com.example.grpccommon.GetArtistResponse> getGetArtistDetailsMethod;
    if ((getGetArtistDetailsMethod = CatalogServiceGrpc.getGetArtistDetailsMethod) == null) {
      synchronized (CatalogServiceGrpc.class) {
        if ((getGetArtistDetailsMethod = CatalogServiceGrpc.getGetArtistDetailsMethod) == null) {
          CatalogServiceGrpc.getGetArtistDetailsMethod = getGetArtistDetailsMethod =
              io.grpc.MethodDescriptor.<com.example.grpccommon.GetArtistRequest, com.example.grpccommon.GetArtistResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetArtistDetails"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpccommon.GetArtistRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpccommon.GetArtistResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CatalogServiceMethodDescriptorSupplier("GetArtistDetails"))
              .build();
        }
      }
    }
    return getGetArtistDetailsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpccommon.GetTrackRequest,
      com.example.grpccommon.GetTrackResponse> getGetTrackDetailsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTrackDetails",
      requestType = com.example.grpccommon.GetTrackRequest.class,
      responseType = com.example.grpccommon.GetTrackResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpccommon.GetTrackRequest,
      com.example.grpccommon.GetTrackResponse> getGetTrackDetailsMethod() {
    io.grpc.MethodDescriptor<com.example.grpccommon.GetTrackRequest, com.example.grpccommon.GetTrackResponse> getGetTrackDetailsMethod;
    if ((getGetTrackDetailsMethod = CatalogServiceGrpc.getGetTrackDetailsMethod) == null) {
      synchronized (CatalogServiceGrpc.class) {
        if ((getGetTrackDetailsMethod = CatalogServiceGrpc.getGetTrackDetailsMethod) == null) {
          CatalogServiceGrpc.getGetTrackDetailsMethod = getGetTrackDetailsMethod =
              io.grpc.MethodDescriptor.<com.example.grpccommon.GetTrackRequest, com.example.grpccommon.GetTrackResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTrackDetails"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpccommon.GetTrackRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpccommon.GetTrackResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CatalogServiceMethodDescriptorSupplier("GetTrackDetails"))
              .build();
        }
      }
    }
    return getGetTrackDetailsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CatalogServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CatalogServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CatalogServiceStub>() {
        @java.lang.Override
        public CatalogServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CatalogServiceStub(channel, callOptions);
        }
      };
    return CatalogServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CatalogServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CatalogServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CatalogServiceBlockingStub>() {
        @java.lang.Override
        public CatalogServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CatalogServiceBlockingStub(channel, callOptions);
        }
      };
    return CatalogServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CatalogServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CatalogServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CatalogServiceFutureStub>() {
        @java.lang.Override
        public CatalogServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CatalogServiceFutureStub(channel, callOptions);
        }
      };
    return CatalogServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getAlbumDetails(com.example.grpccommon.GetAlbumRequest request,
        io.grpc.stub.StreamObserver<com.example.grpccommon.GetAlbumResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAlbumDetailsMethod(), responseObserver);
    }

    /**
     */
    default void getArtistDetails(com.example.grpccommon.GetArtistRequest request,
        io.grpc.stub.StreamObserver<com.example.grpccommon.GetArtistResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetArtistDetailsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Новый метод
     * </pre>
     */
    default void getTrackDetails(com.example.grpccommon.GetTrackRequest request,
        io.grpc.stub.StreamObserver<com.example.grpccommon.GetTrackResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTrackDetailsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service CatalogService.
   */
  public static abstract class CatalogServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CatalogServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service CatalogService.
   */
  public static final class CatalogServiceStub
      extends io.grpc.stub.AbstractAsyncStub<CatalogServiceStub> {
    private CatalogServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CatalogServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CatalogServiceStub(channel, callOptions);
    }

    /**
     */
    public void getAlbumDetails(com.example.grpccommon.GetAlbumRequest request,
        io.grpc.stub.StreamObserver<com.example.grpccommon.GetAlbumResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAlbumDetailsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getArtistDetails(com.example.grpccommon.GetArtistRequest request,
        io.grpc.stub.StreamObserver<com.example.grpccommon.GetArtistResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetArtistDetailsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Новый метод
     * </pre>
     */
    public void getTrackDetails(com.example.grpccommon.GetTrackRequest request,
        io.grpc.stub.StreamObserver<com.example.grpccommon.GetTrackResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetTrackDetailsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service CatalogService.
   */
  public static final class CatalogServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CatalogServiceBlockingStub> {
    private CatalogServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CatalogServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CatalogServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpccommon.GetAlbumResponse getAlbumDetails(com.example.grpccommon.GetAlbumRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAlbumDetailsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpccommon.GetArtistResponse getArtistDetails(com.example.grpccommon.GetArtistRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetArtistDetailsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Новый метод
     * </pre>
     */
    public com.example.grpccommon.GetTrackResponse getTrackDetails(com.example.grpccommon.GetTrackRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTrackDetailsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service CatalogService.
   */
  public static final class CatalogServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<CatalogServiceFutureStub> {
    private CatalogServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CatalogServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CatalogServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpccommon.GetAlbumResponse> getAlbumDetails(
        com.example.grpccommon.GetAlbumRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAlbumDetailsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpccommon.GetArtistResponse> getArtistDetails(
        com.example.grpccommon.GetArtistRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetArtistDetailsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Новый метод
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpccommon.GetTrackResponse> getTrackDetails(
        com.example.grpccommon.GetTrackRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetTrackDetailsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ALBUM_DETAILS = 0;
  private static final int METHODID_GET_ARTIST_DETAILS = 1;
  private static final int METHODID_GET_TRACK_DETAILS = 2;

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
        case METHODID_GET_ALBUM_DETAILS:
          serviceImpl.getAlbumDetails((com.example.grpccommon.GetAlbumRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpccommon.GetAlbumResponse>) responseObserver);
          break;
        case METHODID_GET_ARTIST_DETAILS:
          serviceImpl.getArtistDetails((com.example.grpccommon.GetArtistRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpccommon.GetArtistResponse>) responseObserver);
          break;
        case METHODID_GET_TRACK_DETAILS:
          serviceImpl.getTrackDetails((com.example.grpccommon.GetTrackRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpccommon.GetTrackResponse>) responseObserver);
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
          getGetAlbumDetailsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.grpccommon.GetAlbumRequest,
              com.example.grpccommon.GetAlbumResponse>(
                service, METHODID_GET_ALBUM_DETAILS)))
        .addMethod(
          getGetArtistDetailsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.grpccommon.GetArtistRequest,
              com.example.grpccommon.GetArtistResponse>(
                service, METHODID_GET_ARTIST_DETAILS)))
        .addMethod(
          getGetTrackDetailsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.grpccommon.GetTrackRequest,
              com.example.grpccommon.GetTrackResponse>(
                service, METHODID_GET_TRACK_DETAILS)))
        .build();
  }

  private static abstract class CatalogServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CatalogServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpccommon.CatalogProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CatalogService");
    }
  }

  private static final class CatalogServiceFileDescriptorSupplier
      extends CatalogServiceBaseDescriptorSupplier {
    CatalogServiceFileDescriptorSupplier() {}
  }

  private static final class CatalogServiceMethodDescriptorSupplier
      extends CatalogServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    CatalogServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (CatalogServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CatalogServiceFileDescriptorSupplier())
              .addMethod(getGetAlbumDetailsMethod())
              .addMethod(getGetArtistDetailsMethod())
              .addMethod(getGetTrackDetailsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
