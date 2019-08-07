package com.rockPaperScissors.grpc.gamerps;

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
    comments = "Source: GameRPS.proto")
public final class GameRPSGrpc {

  private GameRPSGrpc() {}

  public static final String SERVICE_NAME = "com.rockPaperScissors.grpc.gamerps.GameRPS";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.rockPaperScissors.grpc.gamerps.UserRequest,
      com.rockPaperScissors.grpc.gamerps.LoginResponse> getLoginUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "loginUser",
      requestType = com.rockPaperScissors.grpc.gamerps.UserRequest.class,
      responseType = com.rockPaperScissors.grpc.gamerps.LoginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.rockPaperScissors.grpc.gamerps.UserRequest,
      com.rockPaperScissors.grpc.gamerps.LoginResponse> getLoginUserMethod() {
    io.grpc.MethodDescriptor<com.rockPaperScissors.grpc.gamerps.UserRequest, com.rockPaperScissors.grpc.gamerps.LoginResponse> getLoginUserMethod;
    if ((getLoginUserMethod = GameRPSGrpc.getLoginUserMethod) == null) {
      synchronized (GameRPSGrpc.class) {
        if ((getLoginUserMethod = GameRPSGrpc.getLoginUserMethod) == null) {
          GameRPSGrpc.getLoginUserMethod = getLoginUserMethod = 
              io.grpc.MethodDescriptor.<com.rockPaperScissors.grpc.gamerps.UserRequest, com.rockPaperScissors.grpc.gamerps.LoginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.rockPaperScissors.grpc.gamerps.GameRPS", "loginUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rockPaperScissors.grpc.gamerps.UserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rockPaperScissors.grpc.gamerps.LoginResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GameRPSMethodDescriptorSupplier("loginUser"))
                  .build();
          }
        }
     }
     return getLoginUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.rockPaperScissors.grpc.gamerps.UserRequest,
      com.rockPaperScissors.grpc.gamerps.SignupResponse> getSignupUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "signupUser",
      requestType = com.rockPaperScissors.grpc.gamerps.UserRequest.class,
      responseType = com.rockPaperScissors.grpc.gamerps.SignupResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.rockPaperScissors.grpc.gamerps.UserRequest,
      com.rockPaperScissors.grpc.gamerps.SignupResponse> getSignupUserMethod() {
    io.grpc.MethodDescriptor<com.rockPaperScissors.grpc.gamerps.UserRequest, com.rockPaperScissors.grpc.gamerps.SignupResponse> getSignupUserMethod;
    if ((getSignupUserMethod = GameRPSGrpc.getSignupUserMethod) == null) {
      synchronized (GameRPSGrpc.class) {
        if ((getSignupUserMethod = GameRPSGrpc.getSignupUserMethod) == null) {
          GameRPSGrpc.getSignupUserMethod = getSignupUserMethod = 
              io.grpc.MethodDescriptor.<com.rockPaperScissors.grpc.gamerps.UserRequest, com.rockPaperScissors.grpc.gamerps.SignupResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.rockPaperScissors.grpc.gamerps.GameRPS", "signupUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rockPaperScissors.grpc.gamerps.UserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rockPaperScissors.grpc.gamerps.SignupResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GameRPSMethodDescriptorSupplier("signupUser"))
                  .build();
          }
        }
     }
     return getSignupUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.rockPaperScissors.grpc.gamerps.NewGameRequest,
      com.rockPaperScissors.grpc.gamerps.NewGameResponse> getNewGameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "newGame",
      requestType = com.rockPaperScissors.grpc.gamerps.NewGameRequest.class,
      responseType = com.rockPaperScissors.grpc.gamerps.NewGameResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.rockPaperScissors.grpc.gamerps.NewGameRequest,
      com.rockPaperScissors.grpc.gamerps.NewGameResponse> getNewGameMethod() {
    io.grpc.MethodDescriptor<com.rockPaperScissors.grpc.gamerps.NewGameRequest, com.rockPaperScissors.grpc.gamerps.NewGameResponse> getNewGameMethod;
    if ((getNewGameMethod = GameRPSGrpc.getNewGameMethod) == null) {
      synchronized (GameRPSGrpc.class) {
        if ((getNewGameMethod = GameRPSGrpc.getNewGameMethod) == null) {
          GameRPSGrpc.getNewGameMethod = getNewGameMethod = 
              io.grpc.MethodDescriptor.<com.rockPaperScissors.grpc.gamerps.NewGameRequest, com.rockPaperScissors.grpc.gamerps.NewGameResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.rockPaperScissors.grpc.gamerps.GameRPS", "newGame"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rockPaperScissors.grpc.gamerps.NewGameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rockPaperScissors.grpc.gamerps.NewGameResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GameRPSMethodDescriptorSupplier("newGame"))
                  .build();
          }
        }
     }
     return getNewGameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.rockPaperScissors.grpc.gamerps.TurnRequest,
      com.rockPaperScissors.grpc.gamerps.TurnResponse> getPlayTurnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "playTurn",
      requestType = com.rockPaperScissors.grpc.gamerps.TurnRequest.class,
      responseType = com.rockPaperScissors.grpc.gamerps.TurnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.rockPaperScissors.grpc.gamerps.TurnRequest,
      com.rockPaperScissors.grpc.gamerps.TurnResponse> getPlayTurnMethod() {
    io.grpc.MethodDescriptor<com.rockPaperScissors.grpc.gamerps.TurnRequest, com.rockPaperScissors.grpc.gamerps.TurnResponse> getPlayTurnMethod;
    if ((getPlayTurnMethod = GameRPSGrpc.getPlayTurnMethod) == null) {
      synchronized (GameRPSGrpc.class) {
        if ((getPlayTurnMethod = GameRPSGrpc.getPlayTurnMethod) == null) {
          GameRPSGrpc.getPlayTurnMethod = getPlayTurnMethod = 
              io.grpc.MethodDescriptor.<com.rockPaperScissors.grpc.gamerps.TurnRequest, com.rockPaperScissors.grpc.gamerps.TurnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.rockPaperScissors.grpc.gamerps.GameRPS", "playTurn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rockPaperScissors.grpc.gamerps.TurnRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rockPaperScissors.grpc.gamerps.TurnResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GameRPSMethodDescriptorSupplier("playTurn"))
                  .build();
          }
        }
     }
     return getPlayTurnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.rockPaperScissors.grpc.gamerps.InforRequest,
      com.rockPaperScissors.grpc.gamerps.HistoryResponse> getGetHistoryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getHistory",
      requestType = com.rockPaperScissors.grpc.gamerps.InforRequest.class,
      responseType = com.rockPaperScissors.grpc.gamerps.HistoryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.rockPaperScissors.grpc.gamerps.InforRequest,
      com.rockPaperScissors.grpc.gamerps.HistoryResponse> getGetHistoryMethod() {
    io.grpc.MethodDescriptor<com.rockPaperScissors.grpc.gamerps.InforRequest, com.rockPaperScissors.grpc.gamerps.HistoryResponse> getGetHistoryMethod;
    if ((getGetHistoryMethod = GameRPSGrpc.getGetHistoryMethod) == null) {
      synchronized (GameRPSGrpc.class) {
        if ((getGetHistoryMethod = GameRPSGrpc.getGetHistoryMethod) == null) {
          GameRPSGrpc.getGetHistoryMethod = getGetHistoryMethod = 
              io.grpc.MethodDescriptor.<com.rockPaperScissors.grpc.gamerps.InforRequest, com.rockPaperScissors.grpc.gamerps.HistoryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.rockPaperScissors.grpc.gamerps.GameRPS", "getHistory"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rockPaperScissors.grpc.gamerps.InforRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rockPaperScissors.grpc.gamerps.HistoryResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GameRPSMethodDescriptorSupplier("getHistory"))
                  .build();
          }
        }
     }
     return getGetHistoryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.rockPaperScissors.grpc.gamerps.InforRequest,
      com.rockPaperScissors.grpc.gamerps.Top100Reponse> getGetTop100Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getTop100",
      requestType = com.rockPaperScissors.grpc.gamerps.InforRequest.class,
      responseType = com.rockPaperScissors.grpc.gamerps.Top100Reponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.rockPaperScissors.grpc.gamerps.InforRequest,
      com.rockPaperScissors.grpc.gamerps.Top100Reponse> getGetTop100Method() {
    io.grpc.MethodDescriptor<com.rockPaperScissors.grpc.gamerps.InforRequest, com.rockPaperScissors.grpc.gamerps.Top100Reponse> getGetTop100Method;
    if ((getGetTop100Method = GameRPSGrpc.getGetTop100Method) == null) {
      synchronized (GameRPSGrpc.class) {
        if ((getGetTop100Method = GameRPSGrpc.getGetTop100Method) == null) {
          GameRPSGrpc.getGetTop100Method = getGetTop100Method = 
              io.grpc.MethodDescriptor.<com.rockPaperScissors.grpc.gamerps.InforRequest, com.rockPaperScissors.grpc.gamerps.Top100Reponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.rockPaperScissors.grpc.gamerps.GameRPS", "getTop100"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rockPaperScissors.grpc.gamerps.InforRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.rockPaperScissors.grpc.gamerps.Top100Reponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GameRPSMethodDescriptorSupplier("getTop100"))
                  .build();
          }
        }
     }
     return getGetTop100Method;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GameRPSStub newStub(io.grpc.Channel channel) {
    return new GameRPSStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GameRPSBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GameRPSBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GameRPSFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GameRPSFutureStub(channel);
  }

  /**
   */
  public static abstract class GameRPSImplBase implements io.grpc.BindableService {

    /**
     */
    public void loginUser(com.rockPaperScissors.grpc.gamerps.UserRequest request,
        io.grpc.stub.StreamObserver<com.rockPaperScissors.grpc.gamerps.LoginResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLoginUserMethod(), responseObserver);
    }

    /**
     */
    public void signupUser(com.rockPaperScissors.grpc.gamerps.UserRequest request,
        io.grpc.stub.StreamObserver<com.rockPaperScissors.grpc.gamerps.SignupResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSignupUserMethod(), responseObserver);
    }

    /**
     */
    public void newGame(com.rockPaperScissors.grpc.gamerps.NewGameRequest request,
        io.grpc.stub.StreamObserver<com.rockPaperScissors.grpc.gamerps.NewGameResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getNewGameMethod(), responseObserver);
    }

    /**
     */
    public void playTurn(com.rockPaperScissors.grpc.gamerps.TurnRequest request,
        io.grpc.stub.StreamObserver<com.rockPaperScissors.grpc.gamerps.TurnResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPlayTurnMethod(), responseObserver);
    }

    /**
     */
    public void getHistory(com.rockPaperScissors.grpc.gamerps.InforRequest request,
        io.grpc.stub.StreamObserver<com.rockPaperScissors.grpc.gamerps.HistoryResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetHistoryMethod(), responseObserver);
    }

    /**
     */
    public void getTop100(com.rockPaperScissors.grpc.gamerps.InforRequest request,
        io.grpc.stub.StreamObserver<com.rockPaperScissors.grpc.gamerps.Top100Reponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTop100Method(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLoginUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.rockPaperScissors.grpc.gamerps.UserRequest,
                com.rockPaperScissors.grpc.gamerps.LoginResponse>(
                  this, METHODID_LOGIN_USER)))
          .addMethod(
            getSignupUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.rockPaperScissors.grpc.gamerps.UserRequest,
                com.rockPaperScissors.grpc.gamerps.SignupResponse>(
                  this, METHODID_SIGNUP_USER)))
          .addMethod(
            getNewGameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.rockPaperScissors.grpc.gamerps.NewGameRequest,
                com.rockPaperScissors.grpc.gamerps.NewGameResponse>(
                  this, METHODID_NEW_GAME)))
          .addMethod(
            getPlayTurnMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.rockPaperScissors.grpc.gamerps.TurnRequest,
                com.rockPaperScissors.grpc.gamerps.TurnResponse>(
                  this, METHODID_PLAY_TURN)))
          .addMethod(
            getGetHistoryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.rockPaperScissors.grpc.gamerps.InforRequest,
                com.rockPaperScissors.grpc.gamerps.HistoryResponse>(
                  this, METHODID_GET_HISTORY)))
          .addMethod(
            getGetTop100Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.rockPaperScissors.grpc.gamerps.InforRequest,
                com.rockPaperScissors.grpc.gamerps.Top100Reponse>(
                  this, METHODID_GET_TOP100)))
          .build();
    }
  }

  /**
   */
  public static final class GameRPSStub extends io.grpc.stub.AbstractStub<GameRPSStub> {
    private GameRPSStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GameRPSStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameRPSStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GameRPSStub(channel, callOptions);
    }

    /**
     */
    public void loginUser(com.rockPaperScissors.grpc.gamerps.UserRequest request,
        io.grpc.stub.StreamObserver<com.rockPaperScissors.grpc.gamerps.LoginResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLoginUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void signupUser(com.rockPaperScissors.grpc.gamerps.UserRequest request,
        io.grpc.stub.StreamObserver<com.rockPaperScissors.grpc.gamerps.SignupResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSignupUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void newGame(com.rockPaperScissors.grpc.gamerps.NewGameRequest request,
        io.grpc.stub.StreamObserver<com.rockPaperScissors.grpc.gamerps.NewGameResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNewGameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void playTurn(com.rockPaperScissors.grpc.gamerps.TurnRequest request,
        io.grpc.stub.StreamObserver<com.rockPaperScissors.grpc.gamerps.TurnResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPlayTurnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getHistory(com.rockPaperScissors.grpc.gamerps.InforRequest request,
        io.grpc.stub.StreamObserver<com.rockPaperScissors.grpc.gamerps.HistoryResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetHistoryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTop100(com.rockPaperScissors.grpc.gamerps.InforRequest request,
        io.grpc.stub.StreamObserver<com.rockPaperScissors.grpc.gamerps.Top100Reponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTop100Method(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GameRPSBlockingStub extends io.grpc.stub.AbstractStub<GameRPSBlockingStub> {
    private GameRPSBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GameRPSBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameRPSBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GameRPSBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.rockPaperScissors.grpc.gamerps.LoginResponse loginUser(com.rockPaperScissors.grpc.gamerps.UserRequest request) {
      return blockingUnaryCall(
          getChannel(), getLoginUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.rockPaperScissors.grpc.gamerps.SignupResponse signupUser(com.rockPaperScissors.grpc.gamerps.UserRequest request) {
      return blockingUnaryCall(
          getChannel(), getSignupUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.rockPaperScissors.grpc.gamerps.NewGameResponse newGame(com.rockPaperScissors.grpc.gamerps.NewGameRequest request) {
      return blockingUnaryCall(
          getChannel(), getNewGameMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.rockPaperScissors.grpc.gamerps.TurnResponse playTurn(com.rockPaperScissors.grpc.gamerps.TurnRequest request) {
      return blockingUnaryCall(
          getChannel(), getPlayTurnMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.rockPaperScissors.grpc.gamerps.HistoryResponse getHistory(com.rockPaperScissors.grpc.gamerps.InforRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetHistoryMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.rockPaperScissors.grpc.gamerps.Top100Reponse getTop100(com.rockPaperScissors.grpc.gamerps.InforRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetTop100Method(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GameRPSFutureStub extends io.grpc.stub.AbstractStub<GameRPSFutureStub> {
    private GameRPSFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GameRPSFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameRPSFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GameRPSFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.rockPaperScissors.grpc.gamerps.LoginResponse> loginUser(
        com.rockPaperScissors.grpc.gamerps.UserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLoginUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.rockPaperScissors.grpc.gamerps.SignupResponse> signupUser(
        com.rockPaperScissors.grpc.gamerps.UserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSignupUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.rockPaperScissors.grpc.gamerps.NewGameResponse> newGame(
        com.rockPaperScissors.grpc.gamerps.NewGameRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getNewGameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.rockPaperScissors.grpc.gamerps.TurnResponse> playTurn(
        com.rockPaperScissors.grpc.gamerps.TurnRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPlayTurnMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.rockPaperScissors.grpc.gamerps.HistoryResponse> getHistory(
        com.rockPaperScissors.grpc.gamerps.InforRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetHistoryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.rockPaperScissors.grpc.gamerps.Top100Reponse> getTop100(
        com.rockPaperScissors.grpc.gamerps.InforRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTop100Method(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOGIN_USER = 0;
  private static final int METHODID_SIGNUP_USER = 1;
  private static final int METHODID_NEW_GAME = 2;
  private static final int METHODID_PLAY_TURN = 3;
  private static final int METHODID_GET_HISTORY = 4;
  private static final int METHODID_GET_TOP100 = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GameRPSImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GameRPSImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGIN_USER:
          serviceImpl.loginUser((com.rockPaperScissors.grpc.gamerps.UserRequest) request,
              (io.grpc.stub.StreamObserver<com.rockPaperScissors.grpc.gamerps.LoginResponse>) responseObserver);
          break;
        case METHODID_SIGNUP_USER:
          serviceImpl.signupUser((com.rockPaperScissors.grpc.gamerps.UserRequest) request,
              (io.grpc.stub.StreamObserver<com.rockPaperScissors.grpc.gamerps.SignupResponse>) responseObserver);
          break;
        case METHODID_NEW_GAME:
          serviceImpl.newGame((com.rockPaperScissors.grpc.gamerps.NewGameRequest) request,
              (io.grpc.stub.StreamObserver<com.rockPaperScissors.grpc.gamerps.NewGameResponse>) responseObserver);
          break;
        case METHODID_PLAY_TURN:
          serviceImpl.playTurn((com.rockPaperScissors.grpc.gamerps.TurnRequest) request,
              (io.grpc.stub.StreamObserver<com.rockPaperScissors.grpc.gamerps.TurnResponse>) responseObserver);
          break;
        case METHODID_GET_HISTORY:
          serviceImpl.getHistory((com.rockPaperScissors.grpc.gamerps.InforRequest) request,
              (io.grpc.stub.StreamObserver<com.rockPaperScissors.grpc.gamerps.HistoryResponse>) responseObserver);
          break;
        case METHODID_GET_TOP100:
          serviceImpl.getTop100((com.rockPaperScissors.grpc.gamerps.InforRequest) request,
              (io.grpc.stub.StreamObserver<com.rockPaperScissors.grpc.gamerps.Top100Reponse>) responseObserver);
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

  private static abstract class GameRPSBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GameRPSBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.rockPaperScissors.grpc.gamerps.GameRPSOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GameRPS");
    }
  }

  private static final class GameRPSFileDescriptorSupplier
      extends GameRPSBaseDescriptorSupplier {
    GameRPSFileDescriptorSupplier() {}
  }

  private static final class GameRPSMethodDescriptorSupplier
      extends GameRPSBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GameRPSMethodDescriptorSupplier(String methodName) {
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
      synchronized (GameRPSGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GameRPSFileDescriptorSupplier())
              .addMethod(getLoginUserMethod())
              .addMethod(getSignupUserMethod())
              .addMethod(getNewGameMethod())
              .addMethod(getPlayTurnMethod())
              .addMethod(getGetHistoryMethod())
              .addMethod(getGetTop100Method())
              .build();
        }
      }
    }
    return result;
  }
}
