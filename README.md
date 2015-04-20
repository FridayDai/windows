Ratchet V2 Admin Portal
================

## Dependencies

1. Install **JDK1.8**

2. Install **gvm**

	```
    curl -s get.gvmtool.net | bash
    ```
3. Install **grails**

	```
	gvm install grails 2.4.4			
	```
	
	
## Compass Optional
	
#### (You should choose one of them)
	
1. Install **compass-sass plugin** (**option one**)
	
	- Add plugins in buildConfig.groovy(**Already add**)
	
	```
   		compile ":compass-sass:0.7"
        runtime ":resources:1.2.13"
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
       rbenv install jruby-1.7.9
       rbenv global jruby-1.7.9

       ruby -v
       # jruby-1.7.9
    ```
	 - Install **Compass**
  
   ```
   		gem update --system
   		gem install compass
   		gem install sass-lint
   ```

	- Setup **JRuby** Path
  		 - Set up the ruby environment for compass-sass plugin
   
  	 ```
      cd /etc
      sudo vim launchd.conf
      # add the following line to it
      # your-JRuby-Path should be like: ~/.rbenv/shims/jruby
      setenv PATH /usr/bin:/bin:/usr/sbin:/sbin:/usr/local/bin:your-JRuby-Path
      # restarted your machine so that the new launchd config would take effect
  	 ```

2. Not Install **compass-sass plugin** (**option two**)
	
	- remove plugins in buildConfig.groovy
	
	```
   		//compile ":compass-sass:0.7"
        //runtime ":resources:1.2.13"
    ```
    
     - Install **Compass**
  
   ```
   		gem update --system
   		gem install compass
   		gem install sass-lint
   ```


## Synchronize Grails Settings


1. If you **use jRuby** 	

	```
	bower install
	npm install
```


2. If you **don't use jRuby**


	```
	cd /ratchet-v2-admin-portal/grails-app/assets/stylesheets
	compass watch	
```

	```
	bower install
	npm install
```

## Running 

1. Using **remote** backend server **http://54.164.12.147:8091/api/v1**

	```
  grails run-app
```

2. Using **locally** running backend server

	```
  vim grails-app/conf/resources/noredist/override.properties
  # change ratchetv2.server.url.base = http://54.164.12.147:8091/api/v1 to
  ratchetv2.server.url.base = http://localhost:8090/api/v1
```

	- navigate to the backend server project

	```
  cd /where-your-projects-are/ratchet-v2-server/
```

    - (**Note**: if you haven't already, follow the 'Setup database' instructions in the README)

	```
  rabbitmq-server
```

   - Open a new console tab (same dir)
  
	```      
	./grailsw run-app -Dserver.port=8090
	```

  - Open a new console tab (same dir)
	
  ```	  
  cd /where-your-projects-are/ratchet-v2-admin-portal
  ./grailsw run-app
```
	- See http://localhost:8080

## CDN
1. The variable CDN_ENABLE should be set as 'true' in env configuration.
2. Set one custom origin instance of clouldfront
2. The variable CDN_ASSET_DOMAIN_ADMIN should be set with clouldfront instance domain name


## Configuration Variables

- ELK_TCP_ADDR
- SERVER_URL
- CDN_ENABLE    // true | false | not define
- CDN_ASSET_DOMAIN_ADMIN    // just cloudfront url domain like: ```d1gdqclzwn7f9.cloudfront.net```
