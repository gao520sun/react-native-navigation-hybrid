/**
 * react-native-navigation-hybrid
 * https://github.com/listenzz/react-native-navigation-hybrid
 * @flow
 */

import React, { Component } from 'react';
import { Text, View, TouchableOpacity, ScrollView, Slider } from 'react-native';

import styles from './Styles';

export default class TopBarAlpha extends Component {
  static navigationItem = {
    topBarAlpha: 0.5,
  };

  constructor(props) {
    super(props);
    this.topBarTitleView = this.topBarTitleView.bind(this);
    this.topBarHidden = this.topBarHidden.bind(this);
    this.topBarColor = this.topBarColor.bind(this);
    this.topBarAlpha = this.topBarAlpha.bind(this);
    this.onAlphaChange = this.onAlphaChange.bind(this);
    this.state = { alpha: 0.5 };
  }

  topBarHidden() {
    this.props.navigator.push('TopBarHidden');
  }

  topBarColor() {
    this.props.navigator.push('TopBarColor');
  }

  topBarAlpha() {
    this.props.navigator.push('TopBarAlpha');
  }

  topBarTitleView() {
    this.props.navigator.push('TopBarTitleView');
  }

  onAlphaChange(value) {
    this.props.garden.setTopBarAlpha({
      topBarAlpha: value,
    });
    this.setState({ alpha: value });
  }

  render() {
    return (
      <ScrollView>
        <View style={[styles.container, { paddingTop: 36 }]}>
          <Text style={styles.welcome}>滑动看看</Text>

          <TouchableOpacity onPress={this.topBarHidden} activeOpacity={0.2} style={styles.button}>
            <Text style={styles.buttonText}>TopBar hidden</Text>
          </TouchableOpacity>

          <TouchableOpacity onPress={this.topBarColor} activeOpacity={0.2} style={styles.button}>
            <Text style={styles.buttonText}>TopBar color</Text>
          </TouchableOpacity>

          <TouchableOpacity onPress={this.topBarAlpha} activeOpacity={0.2} style={styles.button}>
            <Text style={styles.buttonText}>TopBar alpha</Text>
          </TouchableOpacity>

          <TouchableOpacity
            onPress={this.topBarTitleView}
            activeOpacity={0.2}
            style={styles.button}
          >
            <Text style={styles.buttonText}>TopBar title view</Text>
          </TouchableOpacity>

          <Slider
            style={{ marginLeft: 32, marginRight: 32 }}
            onValueChange={this.onAlphaChange}
            step={0.01}
            value={this.state.alpha}
          />

          <Text style={styles.result}>alpha: {this.state.alpha}</Text>
        </View>
      </ScrollView>
    );
  }
}
