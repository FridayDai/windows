Ratchet V2 Admin Portal
================

## Dependencies

- Install **JDK1.7+**

- Install **gvm**

	```
    curl -s get.gvmtool.net | bash
    ```
- Install **grails**

	```
	gvm install grails 2.4.4		
	```
- Install **Homebrew**
    
    ```
    ruby -e '$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)'
    ```
- Install **Ruby** 
    
   - Now that we have Homebrew installed, we can use it to install Ruby. 
   	 We're going to use rbenv to install and manage our Ruby versions. 
   	 To do this, run the following commands in your Terminal:
   
    ```
       brew install rbenv ruby-build
       
       # Add rbenv to bash so that it loads every time you open a terminal
       echo 'if which rbenv > /dev/null; then eval "$(rbenv init -)"; fi' >> ~/.bash_profile
       source ~/.bash_profile

       # Install Ruby 2.1.3 and set it as the default version
       rbenv install jruby 1.7.9
       rbenv global jruby 1.7.9

       ruby -v
       # jruby 1.7.9
    ```
 - Install **Compass**
  
   ```
   		gem update --system
   		gem install compass
   ```

- Setup **JRuby** Path
   - Set up the ruby environment for compass-sass plugin
   
  	 ```
      cd /etc
      sudo vim launchd.conf
      # added the following line to it
      setenv PATH /usr/bin:/bin:/usr/sbin:/sbin:/usr/local/bin:your-JRuby-Path
      # restarted your machine so that the new launchd config would take effect
  	 ```



## Run
	
```
	bower install
	grails run-app
```
