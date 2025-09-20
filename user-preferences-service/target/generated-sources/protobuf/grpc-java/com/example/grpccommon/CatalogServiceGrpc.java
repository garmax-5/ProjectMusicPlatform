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
  private static volatile io.grpc.MethodDescriptor<com.example.grpccommon.CheckAlbumRequest,
      com.example.grpccommon.ExistsResponse> getCheckAlbumExistsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckAlbumExists",
      requestType = com.example.grpccommon.CheckAlbumRequest.class,
      responseType = com.example.grpccommon.ExistsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpccommon.CheckAlbumRequest,
      com.example.grpccommon.ExistsResponse> getCheckAlbumExistsMethod() {
    io.grpc.MethodDescriptor<com.example.grpccommon.CheckAlbumRequest, com.example.grpccommon.ExistsResponse> getCheckAlbumExistsMethod;
    if ((getCheckAlbumExistsMethod = CatalogServiceGrpc.getCheckAlbumExistsMethod) == null) {
      synchronized (CatalogServiceGrpc.class) {
        if ((getCheckAlbumExistsMethod = CatalogServiceGrpc.getCheckAlbumExistsMethod) == null) {
          CatalogServiceGrpc.getCheckAlbumExistsMethod = getCheckAlbumExistsMethod =
              io.grpc.MethodDescriptor.<com.example.grpccommon.CheckAlbumRequest, com.example.grpccommon.ExistsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckAlbumExists"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpccommon.CheckAlbumRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpccommon.ExistsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CatalogServiceMethodDescriptorSupplier("CheckAlbumExists"))
              .build();
        }
      }
    }
    return getCheckAlbumExistsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpccommon.CheckArtistRequest,
      com.example.grpccommon.ExistsResponse> getCheckArtistExistsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckArtistExists",
      requestType = com.example.grpccommon.CheckArtistRequest.class,
      responseType = com.example.grpccommon.ExistsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpccommon.CheckArtistRequest,
      com.example.grpccommon.ExistsResponse> getCheckArtistExistsMethod() {
    io.grpc.MethodDescriptor<com.example.grpccommon.CheckArtistRequest, com.example.grpccommon.ExistsResponse> getCheckArtistExistsMethod;
    if ((getCheckArtistExistsMethod = CatalogServiceGrpc.getCheckArtistExistsMethod) == null) {
      synchronized (CatalogServiceGrpc.class) {
        if ((getCheckArtistExistsMethod = CatalogServiceGrpc.getCheckArtistExistsMethod) == null) {
          CatalogServiceGrpc.getCheckArtistExistsMethod = getCheckArtistExistsMethod =
              io.grpc.MethodDescriptor.<com.example.grpccommon.CheckArtistRequest, com.example.grpccommon.ExistsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckArtistExists"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpccommon.CheckArtistRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpccommon.ExistsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CatalogServiceMethodDescriptorSupplier("CheckArtistExists"))
              .build();
        }
      }
    }
    return getCheckArtistExistsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpccommon.CheckTrackRequest,
      com.example.grpccommon.TrackResponse> getGetTrackByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTrackById",
      requestType = com.example.grpccommon.CheckTrackRequest.class,
      responseType = com.example.grpccommon.TrackResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpccommon.CheckTrackRequest,
      com.example.grpccommon.TrackResponse> getGetTrackByIdMethod() {
    io.grpc.MethodDescriptor<com.example.grpccommon.CheckTrackRequest, com.example.grpccommon.TrackResponse> getGetTrackByIdMethod;
    if ((getGetTrackByIdMethod = CatalogServiceGrpc.getGetTrackByIdMethod) == null) {
      synchronized (CatalogServiceGrpc.class) {
        if ((getGetTrackByIdMethod = CatalogServiceGrpc.getGetTrackByIdMethod) == null) {
          CatalogServiceGrpc.getGetTrackByIdMethod = getGetTrackByIdMethod =
              io.grpc.MethodDescriptor.<com.example.grpccommon.CheckTrackRequest, com.example.grpccommon.TrackResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTrackById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpccommon.CheckTrackRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpccommon.TrackResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CatalogServiceMethodDescriptorSupplier("GetTrackById"))
              .build();
        }
      }
    }
    return getGetTrackByIdMethod;
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
     * <pre>
     * Новые методы для проверки существования
     * </pre>
     */
    default void checkAlbumExists(com.example.grpccommon.CheckAlbumRequest request,
        io.grpc.stub.StreamObserver<com.example.grpccommon.ExistsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckAlbumExistsMethod(), responseObserver);
    }

    /**
     */
    default void checkArtistExists(com.example.grpccommon.CheckArtistRequest request,
        io.grpc.stub.StreamObserver<com.example.grpccommon.ExistsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckArtistExistsMethod(), responseObserver);
    }

    /**
     */
    default void getTrackById(com.example.grpccommon.CheckTrackRequest request,
        io.grpc.stub.StreamObserver<com.example.grpccommon.TrackResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTrackByIdMethod(), responseObserver);
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
     * <pre>
     * Новые методы для проверки существования
     * </pre>
     */
    public void checkAlbumExists(com.example.grpccommon.CheckAlbumRequest request,
        io.grpc.stub.StreamObserver<com.example.grpccommon.ExistsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckAlbumExistsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkArtistExists(com.example.grpccommon.CheckArtistRequest request,
        io.grpc.stub.StreamObserver<com.example.grpccommon.ExistsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckArtistExistsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTrackById(com.example.grpccommon.CheckTrackRequest request,
        io.grpc.stub.StreamObserver<com.example.grpccommon.TrackResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetTrackByIdMethod(), getCallOptions()), request, responseObserver);
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
     * <pre>
     * Новые методы для проверки существования
     * </pre>
     */
    public com.example.grpccommon.ExistsResponse checkAlbumExists(com.example.grpccommon.CheckAlbumRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckAlbumExistsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpccommon.ExistsResponse checkArtistExists(com.example.grpccommon.CheckArtistRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckArtistExistsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpccommon.TrackResponse getTrackById(com.example.grpccommon.CheckTrackRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTrackByIdMethod(), getCallOptions(), request);
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
     * <pre>
     * Новые методы для проверки существования
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpccommon.ExistsResponse> checkAlbumExists(
        com.example.grpccommon.CheckAlbumRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckAlbumExistsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpccommon.ExistsResponse> checkArtistExists(
        com.example.grpccommon.CheckArtistRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckArtistExistsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpccommon.TrackResponse> getTrackById(
        com.example.grpccommon.CheckTrackRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetTrackByIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_ALBUM_EXISTS = 0;
  private static final int METHODID_CHECK_ARTIST_EXISTS = 1;
  private static final int METHODID_GET_TRACK_BY_ID = 2;

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
        case METHODID_CHECK_ALBUM_EXISTS:
          serviceImpl.checkAlbumExists((com.example.grpccommon.CheckAlbumRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpccommon.ExistsResponse>) responseObserver);
          break;
        case METHODID_CHECK_ARTIST_EXISTS:
          serviceImpl.checkArtistExists((com.example.grpccommon.CheckArtistRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpccommon.ExistsResponse>) responseObserver);
          break;
        case METHODID_GET_TRACK_BY_ID:
          serviceImpl.getTrackById((com.example.grpccommon.CheckTrackRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpccommon.TrackResponse>) responseObserver);
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
          getCheckAlbumExistsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.grpccommon.CheckAlbumRequest,
              com.example.grpccommon.ExistsResponse>(
                service, METHODID_CHECK_ALBUM_EXISTS)))
        .addMethod(
          getCheckArtistExistsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.grpccommon.CheckArtistRequest,
              com.example.grpccommon.ExistsResponse>(
                service, METHODID_CHECK_ARTIST_EXISTS)))
        .addMethod(
          getGetTrackByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.grpccommon.CheckTrackRequest,
              com.example.grpccommon.TrackResponse>(
                service, METHODID_GET_TRACK_BY_ID)))
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
              .addMethod(getCheckAlbumExistsMethod())
              .addMethod(getCheckArtistExistsMethod())
              .addMethod(getGetTrackByIdMethod())
              .build();
        }
      }
    }
    return result;
  }
}
