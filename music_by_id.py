# coding=utf-8
from com.android.monkeyrunner import MonkeyRunner,MonkeyDevice
from com.android.monkeyrunner.easy import EasyMonkeyDevice
from com.android.monkeyrunner.easy import By
from com.android.chimpchat.hierarchyviewer import HierarchyViewer
from com.android.hierarchyviewerlib.device import ViewNode

device = MonkeyRunner.waitForConnection()

# Installs the Android package. Notice that this method returns a boolean, so you can test
# to see if the installation worked.
flag = device.installPackage('/Users/zhang/Documents/workspace_backup/MusicPlayerRepo/bin/MusicPlayerRepo.apk')

print "install flag= %s" % flag

# sets a variable with the package's internal name
package = 'com.chendong.music'

# sets a variable with the name of an Activity in the package
activity = 'com.chendong.music.MainActivity'

# sets the name of the component to start
runComponent = package + '/' + activity

# Runs the component
flag = device.startActivity(component=runComponent)

MonkeyRunner.sleep(3)

# action 2 , click music list
#device.touch(160,270,'DOWN_AND_UP')

#easy_device = EasyMonkeyDevice(device)
hierarchy_viewer = device.getHierarchyViewer()
list = hierarchy_viewer.findViewById('id/local_list')

childList = list.children

if childList:
   firstNode = childList[0]
   first = hierarchy_viewer.getAbsolutePositionOfView(firstNode)

   device.touch(first.x + 20,first.y+10,'DOWN_AND_UP')
   print "x= %d , y = %d " % (first.x , first.y)
   
   MonkeyRunner.sleep(5)

   stopNode = hierarchy_viewer.findViewById('id/stop')
   stop = hierarchy_viewer.getAbsolutePositionOfView(stopNode)
    
   device.touch(stop.x,stop.y,'DOWN_AND_UP')
   print "stop:x= %d , y = %d " % (stop.x , stop.y)
   MonkeyRunner.sleep(3)

   
   startNode =hierarchy_viewer.findViewById('id/start')
   start = hierarchy_viewer.getAbsolutePositionOfView(startNode)
   
   device.touch(start.x,start.y,'DOWN_AND_UP')
   print "start = %d , y = %d " % (start.x , start.y)

   MonkeyRunner.sleep(5)

   pauseNode = hierarchy_viewer.findViewById('id/pause')
   pause = hierarchy_viewer.getAbsolutePositionOfView(pauseNode)
   device.touch(pause.x,pause.y,'DOWN_AND_UP')
   print "pause:x= %d , y = %d " % (pause.x , pause.y)

   MonkeyRunner.sleep(3)

   device.press('KEYCODE_BACK','DOWN_AND_UP')
   MonkeyRunner.sleep(3)

   action_bar = hierarchy_viewer.findViewById('id/action_bar_container')
   scollingTab = action_bar.children[2]
   linearLayout = scollingTab.children[0]
   remoteLayout = linearLayout.children[1]

   remote = hierarchy_viewer.getAbsolutePositionOfView(remoteLayout)

   device.touch(remote.x , remote.y,'DOWN_AND_UP')
   print "remote  x= %d , y = %d " % (remote.x,remote.y)
   MonkeyRunner.sleep(3)

   textNode = hierarchy_viewer.findViewById('id/autoCompleteTextView')
   text = hierarchy_viewer.getAbsolutePositionOfView(textNode)
   device.touch(text.x,text.y,'DOWN_AND_UP')
   print "remote text x= %d , y = %d " % (text.x,text.y)
   MonkeyRunner.sleep(1)

   device.type("http://120.196.211.50/wap/645264.mp3")

   MonkeyRunner.sleep(10)

   textNode = hierarchy_viewer.findViewById('id/autoCompleteTextView')
   text = hierarchy_viewer.getAbsolutePositionOfView(textNode)
   device.touch(text.x,text.y,'DOWN_AND_UP')
   print "remote text x= %d , y = %d " % (text.x,text.y)
   MonkeyRunner.sleep(1)
        
   remote_playNode = hierarchy_viewer.findViewById('id/button1')
   remote_play = hierarchy_viewer.getAbsolutePositionOfView(remote_playNode)

   device.touch(remote_play.x , remote_play.y,'DOWN_AND_UP')
   print "remote Play x= %d , y = %d " % (remote_play.x,remote_play.y)

   MonkeyRunner.sleep(5)

   pauseNode2 = hierarchy_viewer.findViewById('id/pause')
   pause2 = hierarchy_viewer.getAbsolutePositionOfView(pauseNode2)
   device.touch(pause2.x,pause2.y,'DOWN_AND_UP')
   print "pause:x= %d , y = %d " % (pause2.x , pause2.y)



