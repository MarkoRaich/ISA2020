package com.example.protos;

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
    value = "by gRPC proto compiler (version 1.34.0)",
    comments = "Source: PharmacySystemApi_Id1.proto")
public final class DrugAvailabilityGrpc {

  private DrugAvailabilityGrpc() {}

  public static final String SERVICE_NAME = "DrugAvailability";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.protos.FindDrugRequest,
      com.example.protos.FindDrugResponse> getFindDrugMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FindDrug",
      requestType = com.example.protos.FindDrugRequest.class,
      responseType = com.example.protos.FindDrugResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.protos.FindDrugRequest,
      com.example.protos.FindDrugResponse> getFindDrugMethod() {
    io.grpc.MethodDescriptor<com.example.protos.FindDrugRequest, com.example.protos.FindDrugResponse> getFindDrugMethod;
    if ((getFindDrugMethod = DrugAvailabilityGrpc.getFindDrugMethod) == null) {
      synchronized (DrugAvailabilityGrpc.class) {
        if ((getFindDrugMethod = DrugAvailabilityGrpc.getFindDrugMethod) == null) {
          DrugAvailabilityGrpc.getFindDrugMethod = getFindDrugMethod =
              io.grpc.MethodDescriptor.<com.example.protos.FindDrugRequest, com.example.protos.FindDrugResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FindDrug"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.protos.FindDrugRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.protos.FindDrugResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DrugAvailabilityMethodDescriptorSupplier("FindDrug"))
              .build();
        }
      }
    }
    return getFindDrugMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.protos.OrderDrugRequest,
      com.example.protos.OrderDrugResponse> getOrderDrugMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OrderDrug",
      requestType = com.example.protos.OrderDrugRequest.class,
      responseType = com.example.protos.OrderDrugResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.protos.OrderDrugRequest,
      com.example.protos.OrderDrugResponse> getOrderDrugMethod() {
    io.grpc.MethodDescriptor<com.example.protos.OrderDrugRequest, com.example.protos.OrderDrugResponse> getOrderDrugMethod;
    if ((getOrderDrugMethod = DrugAvailabilityGrpc.getOrderDrugMethod) == null) {
      synchronized (DrugAvailabilityGrpc.class) {
        if ((getOrderDrugMethod = DrugAvailabilityGrpc.getOrderDrugMethod) == null) {
          DrugAvailabilityGrpc.getOrderDrugMethod = getOrderDrugMethod =
              io.grpc.MethodDescriptor.<com.example.protos.OrderDrugRequest, com.example.protos.OrderDrugResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OrderDrug"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.protos.OrderDrugRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.protos.OrderDrugResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DrugAvailabilityMethodDescriptorSupplier("OrderDrug"))
              .build();
        }
      }
    }
    return getOrderDrugMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DrugAvailabilityStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DrugAvailabilityStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DrugAvailabilityStub>() {
        @java.lang.Override
        public DrugAvailabilityStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DrugAvailabilityStub(channel, callOptions);
        }
      };
    return DrugAvailabilityStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DrugAvailabilityBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DrugAvailabilityBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DrugAvailabilityBlockingStub>() {
        @java.lang.Override
        public DrugAvailabilityBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DrugAvailabilityBlockingStub(channel, callOptions);
        }
      };
    return DrugAvailabilityBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DrugAvailabilityFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DrugAvailabilityFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DrugAvailabilityFutureStub>() {
        @java.lang.Override
        public DrugAvailabilityFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DrugAvailabilityFutureStub(channel, callOptions);
        }
      };
    return DrugAvailabilityFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class DrugAvailabilityImplBase implements io.grpc.BindableService {

    /**
     */
    public void findDrug(com.example.protos.FindDrugRequest request,
        io.grpc.stub.StreamObserver<com.example.protos.FindDrugResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFindDrugMethod(), responseObserver);
    }

    /**
     */
    public void orderDrug(com.example.protos.OrderDrugRequest request,
        io.grpc.stub.StreamObserver<com.example.protos.OrderDrugResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOrderDrugMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFindDrugMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.protos.FindDrugRequest,
                com.example.protos.FindDrugResponse>(
                  this, METHODID_FIND_DRUG)))
          .addMethod(
            getOrderDrugMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.protos.OrderDrugRequest,
                com.example.protos.OrderDrugResponse>(
                  this, METHODID_ORDER_DRUG)))
          .build();
    }
  }

  /**
   */
  public static final class DrugAvailabilityStub extends io.grpc.stub.AbstractAsyncStub<DrugAvailabilityStub> {
    private DrugAvailabilityStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DrugAvailabilityStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DrugAvailabilityStub(channel, callOptions);
    }

    /**
     */
    public void findDrug(com.example.protos.FindDrugRequest request,
        io.grpc.stub.StreamObserver<com.example.protos.FindDrugResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFindDrugMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void orderDrug(com.example.protos.OrderDrugRequest request,
        io.grpc.stub.StreamObserver<com.example.protos.OrderDrugResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOrderDrugMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DrugAvailabilityBlockingStub extends io.grpc.stub.AbstractBlockingStub<DrugAvailabilityBlockingStub> {
    private DrugAvailabilityBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DrugAvailabilityBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DrugAvailabilityBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.protos.FindDrugResponse findDrug(com.example.protos.FindDrugRequest request) {
      return blockingUnaryCall(
          getChannel(), getFindDrugMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.protos.OrderDrugResponse orderDrug(com.example.protos.OrderDrugRequest request) {
      return blockingUnaryCall(
          getChannel(), getOrderDrugMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DrugAvailabilityFutureStub extends io.grpc.stub.AbstractFutureStub<DrugAvailabilityFutureStub> {
    private DrugAvailabilityFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DrugAvailabilityFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DrugAvailabilityFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.protos.FindDrugResponse> findDrug(
        com.example.protos.FindDrugRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFindDrugMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.protos.OrderDrugResponse> orderDrug(
        com.example.protos.OrderDrugRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOrderDrugMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FIND_DRUG = 0;
  private static final int METHODID_ORDER_DRUG = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DrugAvailabilityImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DrugAvailabilityImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FIND_DRUG:
          serviceImpl.findDrug((com.example.protos.FindDrugRequest) request,
              (io.grpc.stub.StreamObserver<com.example.protos.FindDrugResponse>) responseObserver);
          break;
        case METHODID_ORDER_DRUG:
          serviceImpl.orderDrug((com.example.protos.OrderDrugRequest) request,
              (io.grpc.stub.StreamObserver<com.example.protos.OrderDrugResponse>) responseObserver);
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

  private static abstract class DrugAvailabilityBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DrugAvailabilityBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.protos.PharmacySystemApiId1.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DrugAvailability");
    }
  }

  private static final class DrugAvailabilityFileDescriptorSupplier
      extends DrugAvailabilityBaseDescriptorSupplier {
    DrugAvailabilityFileDescriptorSupplier() {}
  }

  private static final class DrugAvailabilityMethodDescriptorSupplier
      extends DrugAvailabilityBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DrugAvailabilityMethodDescriptorSupplier(String methodName) {
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
      synchronized (DrugAvailabilityGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DrugAvailabilityFileDescriptorSupplier())
              .addMethod(getFindDrugMethod())
              .addMethod(getOrderDrugMethod())
              .build();
        }
      }
    }
    return result;
  }
}
