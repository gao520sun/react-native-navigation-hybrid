//
//  HBDDrawerController.h
//  NavigationHybrid
//
//  Created by Listen on 2018/1/25.
//  Copyright © 2018年 Listen. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface HBDDrawerController : UIViewController

@property (nonatomic, strong, readonly) UIViewController *contentController;
@property (nonatomic, strong, readonly) UIViewController *menuController;

- (void)setContentViewController:(UIViewController *)contentViewController;
- (void)setMenuViewController:(UIViewController *)menuViewController;

- (void)openMenu;
- (void)closeMenu;
- (void)toggleMenu;
- (BOOL)isMenuOpened;

@end
