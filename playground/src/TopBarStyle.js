/**
 * react-native-navigation-hybrid
 * https://github.com/listenzz/react-native-navigation-hybrid
 * @flow
 */

import React, { Component } from 'react';
import { TouchableOpacity, Text, View, ScrollView } from 'react-native';

import styles from './Styles';

export default class TopBarStyle extends Component {
  static navigationItem = {
    topBarStyle: 'light-content',

    titleItem: {
      title: 'TopBar Style',
    },
  };

  constructor(props) {
    super(props);
    this.switchTopBarStyle = this.switchTopBarStyle.bind(this);
    this.statusBarColor = this.statusBarColor.bind(this);
    this.state = { topBarStyle: 'dark-content' };
  }

  switchTopBarStyle() {
    this.props.garden.setTopBarStyle({ topBarStyle: this.state.topBarStyle });
    if (this.state.topBarStyle === 'dark-content') {
      this.setState({ topBarStyle: 'light-content' });
    } else {
      this.setState({ topBarStyle: 'dark-content' });
    }
  }

  statusBarColor() {
    this.props.navigator.push('StatusBarColor');
  }
  render() {
    return (
      <ScrollView contentInsetAdjustmentBehavior="automatic">
        <View style={styles.container}>
          <Text style={styles.welcome}> 1. Android 5.0 以下状态栏文字只能是白的</Text>

          <Text style={styles.welcome}> 2. Android 会根据情况适当修正状态栏的背景色</Text>

          <TouchableOpacity
            onPress={this.switchTopBarStyle}
            activeOpacity={0.2}
            style={styles.button}
          >
            <Text style={styles.buttonText}>switch to {this.state.topBarStyle}</Text>
          </TouchableOpacity>

          <TouchableOpacity onPress={this.statusBarColor} activeOpacity={0.2} style={styles.button}>
            <Text style={styles.buttonText}>StatusBarColor</Text>
          </TouchableOpacity>
        </View>
      </ScrollView>
    );
  }
}
