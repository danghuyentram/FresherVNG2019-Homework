import  abc
from abc import ABC, abstractclassmethod

class database(ABC):

  @abc.abstractproperty
  def addMessage(self):
    pass

  @abc.abstractproperty
  def loadOldMessage(self):
    pass

  @abc.abstractproperty
  def getFriendByUserName(self):
    pass

  # @abc.abstractproperty
  # def getRoomInforByUserName(self):
  #   pass

  @abc.abstractproperty
  def checkUserPassword(self):
    pass

  @abc.abstractproperty
  def isOffline(self):
    pass

  @abc.abstractproperty
  def isOnline(self):
    pass

  @abc.abstractproperty
  def createUserAccount(self):
    pass

  @abc.abstractproperty
  def createChatRoom(self):
    pass

  @abc.abstractproperty
  def createChatMember(self):
    pass

  @abc.abstractproperty
  def findUserByUserNameOrEmail(self):
    pass

  @abc.abstractproperty
  def addFriend(self):
    pass

  @abc.abstractproperty
  def getRoomNameByUserName(self):
    pass
